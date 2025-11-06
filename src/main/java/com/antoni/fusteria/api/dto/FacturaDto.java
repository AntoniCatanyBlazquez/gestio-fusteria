package com.antoni.fusteria.api.dto;

import com.antoni.fusteria.domain.model.IdentificacioClient;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FacturaDto {

    private Long id;
    private String numeroFactura;
    private String nomClient;
    private IdentificacioClient identificacioClient;
    private LocalDate dataEmissio;
    private Double subtotal;
    private Double iva;
    private Double retencioIRPF;
    private Double totalImport;
    private String metodePagament;
    private String observacions;

    public FacturaDto(Long id, String numeroFactura, String nomClient, IdentificacioClient identificacioClient, LocalDate dataEmissio, Double subtotal, Double iva, Double retencioIRPF, Double totalImport, String metodePagament, String observacions) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.nomClient = nomClient;
        this.identificacioClient = identificacioClient;
        this.dataEmissio = dataEmissio;
        this.subtotal = subtotal;
        this.iva = iva;
        this.retencioIRPF = retencioIRPF;
        this.totalImport = totalImport;
        this.metodePagament = metodePagament;
        this.observacions = observacions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public IdentificacioClient getIdentificacioClient() {
        return identificacioClient;
    }

    public void setIdentificacioClient(IdentificacioClient identificacioClient) {
        this.identificacioClient = identificacioClient;
    }

    public LocalDate getDataEmissio() {
        return dataEmissio;
    }

    public void setDataEmissio(LocalDate dataEmissio) {
        this.dataEmissio = dataEmissio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getRetencioIRPF() {
        return retencioIRPF;
    }

    public void setRetencioIRPF(Double retencioIRPF) {
        this.retencioIRPF = retencioIRPF;
    }

    public Double getTotalImport() {
        return totalImport;
    }

    public void setTotalImport(Double totalImport) {
        this.totalImport = totalImport;
    }

    public String getMetodePagament() {
        return metodePagament;
    }

    public void setMetodePagament(String metodePagament) {
        this.metodePagament = metodePagament;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }
}

