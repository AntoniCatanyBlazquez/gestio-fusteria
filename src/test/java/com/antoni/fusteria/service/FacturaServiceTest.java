package com.antoni.fusteria.service;

import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.domain.repository.FacturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    @Test
    void calculsFacturaSonCorrectes() {
        Treball t1 = new Treball();
        t1.setPreu(new BigDecimal("1000.00"));

        Treball t2 = new Treball();
        t2.setPreu(new BigDecimal("500.00"));

        Client client = new Client();
        client.setNom("Joan");
        client.setLlinatge("Garcia");

        Factura factura = new Factura();
        factura.setClient(client);
        factura.setTreballs(List.of(t1, t2));

        when(facturaRepository.findAll()).thenReturn(List.of());
        when(facturaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        facturaService.createFactura(factura);

        assertEquals(new BigDecimal("1500.00"), factura.getSubtotal());

        assertEquals(new BigDecimal("315.00"), factura.getIva());

        assertEquals(new BigDecimal("225.00"), factura.getRetencioIRPF());

        assertEquals(new BigDecimal("1590.00"), factura.getTotal());
    }

    @Test
    void numeroFacturaEsGeneraCorrectament() {
        Treball t = new Treball();
        t.setPreu(new BigDecimal("200.00"));

        Client client = new Client();
        client.setNom("Maria");
        client.setLlinatge("López");

        Factura factura = new Factura();
        factura.setClient(client);
        factura.setTreballs(List.of(t));

        when(facturaRepository.findAll()).thenReturn(List.of());
        when(facturaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        facturaService.createFactura(factura);

        int anyActual = java.time.LocalDate.now().getYear();
        assertEquals(anyActual + "-001", factura.getNumeroFactura());
    }

    @Test
    void facturaAmbTreballsSensePreu() {
        Treball t = new Treball();
        t.setPreu(BigDecimal.ZERO);

        Client client = new Client();
        client.setNom("Pere");
        client.setLlinatge("Mas");

        Factura factura = new Factura();
        factura.setClient(client);
        factura.setTreballs(List.of(t));

        when(facturaRepository.findAll()).thenReturn(List.of());
        when(facturaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        facturaService.createFactura(factura);

        assertEquals(BigDecimal.ZERO, factura.getSubtotal());
        assertEquals(new BigDecimal("0.00"), factura.getTotal());
    }
}
