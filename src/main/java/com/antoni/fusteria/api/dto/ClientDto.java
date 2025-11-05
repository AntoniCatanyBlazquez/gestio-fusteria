package com.antoni.fusteria.api.dto;

public class ClientDto {
    private Long id;
    private String nomClient;
    private String telefonClient;

    public ClientDto(Long id, String nomClient, String telefonClient) {
        this.id = id;
        this.nomClient = nomClient;
        this.telefonClient = telefonClient;
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
}
