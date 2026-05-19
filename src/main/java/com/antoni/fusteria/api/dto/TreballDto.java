package com.antoni.fusteria.api.dto;

import com.antoni.fusteria.domain.model.Estat_Treball;

public class TreballDto {
    private Long id;
    private String nomClient;
    private String titol;
    private String descripcio;
    private String data;
    private Estat_Treball estat;
    private double preu;
    private String materials;
    private byte[] imatge;

    public TreballDto() {}

    public TreballDto(Long id, String nomClient, String titol, String descripcio,
                      String data, Estat_Treball estat, double preu, String materials, byte[] imatge) {
        this.id = id;
        this.nomClient = nomClient;
        this.titol = titol;
        this.descripcio = descripcio;
        this.data = data;
        this.estat = estat;
        this.preu = preu;
        this.materials = materials;
        this.imatge = imatge;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
    public String getTitol() { return titol; }
    public void setTitol(String titol) { this.titol = titol; }
    public String getDescripcio() { return descripcio; }
    public void setDescripcio(String descripcio) { this.descripcio = descripcio; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public Estat_Treball getEstat() { return estat; }
    public void setEstat(Estat_Treball estat) { this.estat = estat; }
    public double getPreu() { return preu; }
    public void setPreu(double preu) { this.preu = preu; }
    public String getMaterials() { return materials; }
    public void setMaterials(String materials) { this.materials = materials; }
    public byte[] getImatge() { return imatge; }
    public void setImatge(byte[] imatge) { this.imatge = imatge; }
}
