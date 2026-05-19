package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.api.dto.FacturaDto;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(
                facturaService.getAllFactures().stream().map(facturaService::toDto).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDto> getFacturaById(@PathVariable Long id) {
        Factura f = facturaService.getFacturaById(id);
        if (f == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(facturaService.toDto(f));
    }

    @PostMapping
    public ResponseEntity<FacturaDto> createFactura(@RequestBody Factura factura) {
        FacturaDto dto = facturaService.createFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        facturaService.deleteFactura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        Factura f = facturaService.getFacturaById(id);
        if (f == null) return ResponseEntity.notFound().build();
        byte[] pdf = facturaService.generarPdfFactura(f);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura-" + f.getNumeroFactura() + ".pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
