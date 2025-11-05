package com.antoni.fusteria.service;

import com.antoni.fusteria.model.Factura;
import com.antoni.fusteria.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
