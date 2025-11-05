package com.antoni.fusteria.api.dto;

public class FacturaDto {

    private Long id;
    private String nomClient;
    private String dataEmissio;
    private Double totalImport;

    public FacturaDto(Long id, Double totalImport, String dataEmissio, String nomClient) {
        this.id = id;
        this.totalImport = totalImport;
        this.dataEmissio = dataEmissio;
        this.nomClient = nomClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getDataEmissio() {
        return dataEmissio;
    }

    public void setDataEmissio(String dataEmissio) {
        this.dataEmissio = dataEmissio;
    }

    public Double getTotalImport() {
        return totalImport;
    }

    public void setTotalImport(Double totalImport) {
        this.totalImport = totalImport;
    }
}

