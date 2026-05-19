package com.antoni.fusteria.api.dto;

import java.time.LocalDate;

public class FacturaDto {
    private Long id;
    private String numeroFactura;
    private String nomClient;
    private String identificacioClient;
    private LocalDate dataEmisio;
    private double subtotal;
    private double iva;
    private double retencioIRPF;
    private double total;
    private String metodePagament;
    private String observacions;

    public FacturaDto() {}

    public FacturaDto(Long id, String numeroFactura, String nomClient, Object identificacioClient,
                      LocalDate dataEmisio, double subtotal, double iva, double retencioIRPF,
                      double total, String metodePagament, String observacions) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.nomClient = nomClient;
        this.identificacioClient = identificacioClient != null ? identificacioClient.toString() : null;
        this.dataEmisio = dataEmisio;
        this.subtotal = subtotal;
        this.iva = iva;
        this.retencioIRPF = retencioIRPF;
        this.total = total;
        this.metodePagament = metodePagament;
        this.observacions = observacions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
    public String getIdentificacioClient() { return identificacioClient; }
    public void setIdentificacioClient(String identificacioClient) { this.identificacioClient = identificacioClient; }
    public LocalDate getDataEmisio() { return dataEmisio; }
    public void setDataEmisio(LocalDate dataEmisio) { this.dataEmisio = dataEmisio; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getRetencioIRPF() { return retencioIRPF; }
    public void setRetencioIRPF(double retencioIRPF) { this.retencioIRPF = retencioIRPF; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getMetodePagament() { return metodePagament; }
    public void setMetodePagament(String metodePagament) { this.metodePagament = metodePagament; }
    public String getObservacions() { return observacions; }
    public void setObservacions(String observacions) { this.observacions = observacions; }
}
