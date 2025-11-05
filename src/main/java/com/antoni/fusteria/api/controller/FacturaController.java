package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Factura>> getAllFactures() {
        List<Factura> factures = facturaService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
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
        if (factura.getObvervacions() != null) {
            existingFactura.setObvervacions(factura.getObvervacions());
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
}