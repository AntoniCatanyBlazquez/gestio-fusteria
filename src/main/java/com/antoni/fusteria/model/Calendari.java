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

    public Calendari() {
    }

    public Calendari(Long id, Treball treball, LocalDate dateEntrada, String comentaris) {
        this.id = id;
        this.treball = treball;
        this.dateEntrada = dateEntrada;
        this.comentaris = comentaris;
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
}
