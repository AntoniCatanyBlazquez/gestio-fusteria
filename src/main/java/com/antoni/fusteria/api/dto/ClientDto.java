package com.antoni.fusteria.api.dto;

public class ClientDto {
    private Long id;
    private String nom;
    private String llinatge;
    private String telefon;
    private String direccio;
    private String email;

    public ClientDto() {}

    public ClientDto(Long id, String nom, String llinatge, String telefon, String direccio, String email) {
        this.id = id;
        this.nom = nom;
        this.llinatge = llinatge;
        this.telefon = telefon;
        this.direccio = direccio;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getLlinatge() { return llinatge; }
    public void setLlinatge(String llinatge) { this.llinatge = llinatge; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public String getDireccio() { return direccio; }
    public void setDireccio(String direccio) { this.direccio = direccio; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
