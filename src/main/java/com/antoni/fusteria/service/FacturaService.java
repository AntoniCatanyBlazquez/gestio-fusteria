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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                factura.getSubtotal().doubleValue(),
                factura.getIva().doubleValue(),
                factura.getRetencioIRPF() != null ? factura.getRetencioIRPF().doubleValue() : 0.0,
                factura.getTotal().doubleValue(),
                factura.getMetodePagament(),
                factura.getObservacions()
        );
    }

    public FacturaDto createFactura(Factura factura){
        BigDecimal subtotal= factura.getTreballs().stream()
                .map(treball -> treball.getPreu().multiply(BigDecimal.valueOf(treball.getPreu().doubleValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal iva = subtotal.multiply(BigDecimal.valueOf(0.21));
        BigDecimal irpf = subtotal.multiply(BigDecimal.valueOf(0.15));

        BigDecimal total = subtotal.add(iva).subtract(irpf);

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
        long count = facturaRepository.countByAny(any);
        return any + "-" + String.format("%03d", count + 1);
    }

    public byte[] generarPdfFactura(Factura factura) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Factura núm. " + factura.getNumeroFactura()));
        document.add(new Paragraph("Data: " + factura.getDataEmisio()));
        document.add(new Paragraph("Client: " + factura.getClient().getNom()));
        document.add(new Paragraph("NIF: " + factura.getIdentificacioClient()));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Treballs realitzats:"));
        for (Treball t : factura.getTreballs()) {
            document.add(new Paragraph("- " + t.getDescripcio() + " (" + t.getPreu() + " €)"));
        }

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Subtotal: " + factura.getSubtotal() + " €"));
        document.add(new Paragraph("IVA: " + factura.getIva() + " €"));
        document.add(new Paragraph("IRPF: -" + factura.getRetencioIRPF() + " €"));
        document.add(new Paragraph("Total: " + factura.getTotal() + " €"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Mètode de pagament: " + factura.getMetodePagament()));
        document.add(new Paragraph("Observacions: " + factura.getObservacions()));

        document.close();
        return baos.toByteArray();
    }
}
