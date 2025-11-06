package com.antoni.fusteria.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    private LocalDate dataEmisio;

    private String numeroFactura;

    private BigDecimal subtotal;

    private BigDecimal retencioIRPF;

    @NotNull
    private BigDecimal total;

    private BigDecimal iva;

    @Column
    @Nullable
    private String observacions;

    @Nullable
    private String metodePagament;

    @ManyToMany
    @JoinTable(
            name = "FacturaTreball",
            joinColumns = @JoinColumn(name = "factura_id"),
            inverseJoinColumns = @JoinColumn(name = "treball_id")
    )
    private List<Treball> treballs;

    public Factura() {}

    public Factura(Long id, Client client, LocalDate dataEmisio, String numeroFactura,
                   BigDecimal subtotal, BigDecimal retencioIRPF, BigDecimal total,
                   BigDecimal iva, @Nullable String observacions, @Nullable String metodePagament,
                   List<Treball> treballs) {
        this.id = id;
        this.client = client;
        this.dataEmisio = dataEmisio;
        this.numeroFactura = numeroFactura;
        this.subtotal = subtotal;
        this.retencioIRPF = retencioIRPF;
        this.total = total;
        this.iva = iva;
        this.observacions = observacions;
        this.metodePagament = metodePagament;
        this.treballs = treballs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public @NotNull LocalDate getDataEmisio() {
        return dataEmisio;
    }

    public void setDataEmisio(@NotNull LocalDate dataEmisio) {
        this.dataEmisio = dataEmisio;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getRetencioIRPF() {
        return retencioIRPF;
    }

    public void setRetencioIRPF(BigDecimal retencioIRPF) {
        this.retencioIRPF = retencioIRPF;
    }

    public @NotNull BigDecimal getTotal() {
        return total;
    }

    public void setTotal(@NotNull BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    @Nullable
    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(@Nullable String observacions) {
        this.observacions = observacions;
    }

    @Nullable
    public String getMetodePagament() {
        return metodePagament;
    }

    public void setMetodePagament(@Nullable String metodePagament) {
        this.metodePagament = metodePagament;
    }

    public List<Treball> getTreballs() {
        return treballs;
    }

    public void setTreballs(List<Treball> treballs) {
        this.treballs = treballs;
    }
}