package com.antoni.fusteria;

import jakarta.persistence.*;

@Entity
public class FacturaTreball {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "treball_id", nullable = false)
    private Treball treball;

    public FacturaTreball() {
    }

    public FacturaTreball(Long id, Factura factura, Treball treball) {
        this.id = id;
        this.factura = factura;
        this.treball = treball;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Treball getTreball() {
        return treball;
    }

    public void setTreball(Treball treball) {
        this.treball = treball;
    }
}
