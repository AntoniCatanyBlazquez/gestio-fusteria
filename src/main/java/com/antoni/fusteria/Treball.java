package com.antoni.fusteria;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Treball {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private String descripcio;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Estat_Treball estat;
    private double preu;
    private String materials;
    @Lob
    private byte[] imatge;

    @ManyToMany(mappedBy = "treballs")
    private List<Factura> factures;

    @OneToMany(mappedBy = "treball", cascade = CascadeType.ALL)
    private List<Calendari> treballCalendari;

    public Treball() {
    }

    public Treball(Long id, Client client, String descripcio, LocalDate data, Estat_Treball estat, double preu, String materials, byte[] imatge, List<Factura> factures, List<Calendari> treballCalendari) {
        this.id = id;
        this.client = client;
        this.descripcio = descripcio;
        this.data = data;
        this.estat = estat;
        this.preu = preu;
        this.materials = materials;
        this.imatge = imatge;
        this.factures = factures;
        this.treballCalendari = treballCalendari;
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

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Estat_Treball getEstat() {
        return estat;
    }

    public void setEstat(Estat_Treball estat) {
        this.estat = estat;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public byte[] getImatge() {
        return imatge;
    }

    public void setImatge(byte[] imatge) {
        this.imatge = imatge;
    }

    public List<Factura> getFactures() {
        return factures;
    }

    public void setFactures(List<Factura> factures) {
        this.factures = factures;
    }
}
