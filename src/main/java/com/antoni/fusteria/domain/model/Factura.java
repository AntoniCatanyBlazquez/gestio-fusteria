package com.antoni.fusteria.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

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
    private LocalDate dataEmisio;
    private BigDecimal total;
    private BigDecimal iva;
    @Column
    @Nullable
    private String obvervacions;

    @ManyToMany
    @JoinTable(
            name = "FacturaTreball",
            joinColumns = @JoinColumn(name = "factura_id"),
            inverseJoinColumns = @JoinColumn(name = "treball_id")
    )

    private List<Treball> treballs;

    public Factura() {
    }

    public Factura(Long id, Client client, LocalDate dataEmisio, BigDecimal total, BigDecimal iva, @Nullable String obvervacions, List<Treball> treballs) {
        this.id = id;
        this.client = client;
        this.dataEmisio = dataEmisio;
        this.total = total;
        this.iva = iva;
        this.obvervacions = obvervacions;
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

    public LocalDate getDataEmisio() {
        return dataEmisio;
    }

    public void setDataEmisio(LocalDate dataEmisio) {
        this.dataEmisio = dataEmisio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    @Nullable
    public String getObvervacions() {
        return obvervacions;
    }

    public void setObvervacions(@Nullable String obvervacions) {
        this.obvervacions = obvervacions;
    }

    public List<Treball> getTreballs() {
        return treballs;
    }

    public void setTreballs(List<Treball> treballs) {
        this.treballs = treballs;
    }
}
