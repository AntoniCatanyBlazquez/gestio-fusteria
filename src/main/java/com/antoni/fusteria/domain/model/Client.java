package com.antoni.fusteria.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String llinatge;
    private int telefon;
    private String direccio;
    @Column
    @Nullable
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Treball> treballs;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Factura> factures;

    public Client() {
    }

    public Client(Long id, String nom, String llinatge, int telefon, String direccio, @Nullable String email, List<Treball> treballs, List<Factura> factures) {
        this.id = id;
        this.nom = nom;
        this.llinatge = llinatge;
        this.telefon = telefon;
        this.direccio = direccio;
        this.email = email;
        this.treballs = treballs;
        this.factures = factures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatge() {
        return llinatge;
    }

    public void setLlinatge(String llinatge) {
        this.llinatge = llinatge;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public List<Treball> getTreballs() {
        return treballs;
    }

    public void setTreballs(List<Treball> treballs) {
        this.treballs = treballs;
    }

    public List<Factura> getFactures() {
        return factures;
    }

    public void setFactures(List<Factura> factures) {
        this.factures = factures;
    }
}
