package com.antoni.fusteria.service;

import com.antoni.fusteria.api.dto.FacturaDto;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.domain.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                factura.getDataEmisio().toString(),
                factura.getSubtotal().doubleValue(),
                factura.getIva().doubleValue(),
                factura.getRetencioIRPF() != null ? factura.getRetencioIRPF().doubleValue() : 0.0,
                factura.getTotal().doubleValue(),
                factura.getMetodePagament(),
                factura.getObservacions()
        );
    }
}
