package com.antoni.fusteria.service;

import com.antoni.fusteria.api.dto.FacturaDto;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.domain.repository.FacturaRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> getAllFactures() {
        return facturaRepository.findAll();
    }

    public Factura getFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    public FacturaDto toDto(Factura factura) {
        return new FacturaDto(
                factura.getId(),
                factura.getNumeroFactura(),
                factura.getClient().getNom() + " " + factura.getClient().getLlinatge(),
                factura.getIdentificacioClient(),
                factura.getDataEmisio(),
                factura.getSubtotal() != null ? factura.getSubtotal().doubleValue() : 0,
                factura.getIva() != null ? factura.getIva().doubleValue() : 0,
                factura.getRetencioIRPF() != null ? factura.getRetencioIRPF().doubleValue() : 0.0,
                factura.getTotal() != null ? factura.getTotal().doubleValue() : 0,
                factura.getMetodePagament(),
                factura.getObservacions()
        );
    }

    public FacturaDto createFactura(Factura factura) {
        // FIX: subtotal = suma dels preus dels treballs (no preu * preu)
        BigDecimal subtotal = factura.getTreballs().stream()
                .map(Treball::getPreu)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal iva = subtotal.multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal irpf = subtotal.multiply(BigDecimal.valueOf(0.15)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(iva).subtract(irpf).setScale(2, RoundingMode.HALF_UP);

        String numeroFactura = generarNumeroFactura();

        factura.setSubtotal(subtotal);
        factura.setIva(iva);
        factura.setRetencioIRPF(irpf);
        factura.setTotal(total);
        factura.setNumeroFactura(numeroFactura);
        factura.setDataEmisio(LocalDate.now());

        Factura saved = facturaRepository.save(factura);
        return toDto(saved);
    }

    private String generarNumeroFactura() {
        int any = LocalDate.now().getYear();
        long count = 0;
        for (Factura f : facturaRepository.findAll()) {
            if (f.getDataEmisio() != null && f.getDataEmisio().getYear() == any) {
                count++;
            }
        }
        return any + "-" + String.format("%03d", count + 1);
    }

    public byte[] generarPdfFactura(Factura factura) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("FACTURA").setBold().setFontSize(20));
        document.add(new Paragraph("Núm. " + factura.getNumeroFactura()).setBold());
        document.add(new Paragraph("Data: " + factura.getDataEmisio()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Client: " + factura.getClient().getNom() + " " + factura.getClient().getLlinatge()));
        document.add(new Paragraph("NIF/DNI: " + factura.getIdentificacioClient()));
        document.add(new Paragraph(" "));

        Table table = new Table(UnitValue.createPercentArray(new float[]{50, 25, 25}))
                .useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("Descripció").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Preu").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));

        for (Treball t : factura.getTreballs()) {
            table.addCell(t.getDescripcio() != null ? t.getDescripcio() : t.getTitol());
            table.addCell(t.getPreu() + " €");
            table.addCell(t.getPreu() + " €");
        }
        document.add(table);
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Subtotal: " + factura.getSubtotal() + " €"));
        document.add(new Paragraph("IVA (21%): " + factura.getIva() + " €"));
        document.add(new Paragraph("Retenció IRPF (15%): -" + factura.getRetencioIRPF() + " €"));
        document.add(new Paragraph("TOTAL: " + factura.getTotal() + " €").setBold().setFontSize(14));
        document.add(new Paragraph(" "));
        if (factura.getMetodePagament() != null)
            document.add(new Paragraph("Mètode de pagament: " + factura.getMetodePagament()));
        if (factura.getObservacions() != null)
            document.add(new Paragraph("Observacions: " + factura.getObservacions()));

        document.close();
        return baos.toByteArray();
    }
}
