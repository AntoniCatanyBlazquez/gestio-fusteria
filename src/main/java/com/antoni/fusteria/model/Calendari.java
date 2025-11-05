package com.antoni.fusteria.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Calendari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treball_id", nullable = false)
    private Treball treball;

    private LocalDate dateEntrada;
    private String comentaris;
    private String titol;

    public Calendari(String titol) {
        this.titol = titol;
    }

    public Calendari(Long id, Treball treball, LocalDate dateEntrada, String comentaris, String titol) {
        this.id = id;
        this.treball = treball;
        this.dateEntrada = dateEntrada;
        this.comentaris = comentaris;
        this.titol = titol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Treball getTreball() {
        return treball;
    }

    public void setTreball(Treball treball) {
        this.treball = treball;
    }

    public LocalDate getDateEntrada() {
        return dateEntrada;
    }

    public void setDateEntrada(LocalDate dateEntrada) {
        this.dateEntrada = dateEntrada;
    }

    public String getComentaris() {
        return comentaris;
    }

    public void setComentaris(String comentaris) {
        this.comentaris = comentaris;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }
}
