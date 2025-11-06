package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.api.dto.FacturaDto;
import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FacturaController {

    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDto>> getAllFactures() {
        List<Factura> factures = facturaService.getAllFactures();
        List<FacturaDto> dtos = factures.stream()
                .map(facturaService::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDto> getFacturaById(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        FacturaDto dto = facturaService.toDto(factura);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura savedFactura = facturaService.saveFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFactura);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        Factura existingFactura = facturaService.getFacturaById(id);

        if (factura.getDataEmisio() != null) {
            existingFactura.setDataEmisio(factura.getDataEmisio());
        }
        if (factura.getTotal() != null) {
            existingFactura.setTotal(factura.getTotal());
        }
        if (factura.getClient() != null) {
            existingFactura.setClient(factura.getClient());
        }
        if (factura.getIva() != null) {
            existingFactura.setIva(factura.getIva());
        }
        if (factura.getObservacions() != null) {
            existingFactura.setObservacions(factura.getObservacions());
        }
        if (existingFactura == null) {
            return ResponseEntity.notFound().build();
        }

        Factura updatedFactura = facturaService.saveFactura(existingFactura);
        return ResponseEntity.ok(updatedFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        facturaService.deleteFactura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<Client> getClientByFacturaId(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura.getClient());
    }

    @GetMapping("/{id}/treballs")
    public ResponseEntity<List<Treball>> getTreballsByFacturaId(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        List<Treball> treballs = factura.getTreballs();
        return ResponseEntity.ok(treballs);
    }

    @GetMapping("/{id}/descarregar")
    public ResponseEntity<byte[]> descarregarFactura(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfBytes = facturaService.generarPdfFactura(factura);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura_" +
                        factura.getNumeroFactura() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}