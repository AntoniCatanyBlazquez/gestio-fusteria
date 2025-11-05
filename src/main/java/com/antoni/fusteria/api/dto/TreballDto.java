package com.antoni.fusteria.api.dto;

public class TreballDto {
    private Long id;
    private String nomClient;
    private String telefonClient;
    private String descripcioTreball;
    private String estatTreball;

    public TreballDto(Long id, String nomClient, String telefonClient, String descripcioTreball, String estatTreball) {
        this.id = id;
        this.nomClient = nomClient;
        this.telefonClient = telefonClient;
        this.descripcioTreball = descripcioTreball;
        this.estatTreball = estatTreball;
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

    public String getTelefonClient() {
        return telefonClient;
    }

    public void setTelefonClient(String telefonClient) {
        this.telefonClient = telefonClient;
    }

    public String getDescripcioTreball() {
        return descripcioTreball;
    }

    public void setDescripcioTreball(String descripcioTreball) {
        this.descripcioTreball = descripcioTreball;
    }

    public String getEstatTreball() {
        return estatTreball;
    }

    public void setEstatTreball(String estatTreball) {
        this.estatTreball = estatTreball;
    }
}
