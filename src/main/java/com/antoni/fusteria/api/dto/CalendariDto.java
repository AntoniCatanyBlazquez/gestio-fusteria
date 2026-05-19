package com.antoni.fusteria.api.dto;

import java.time.LocalDate;

public class CalendariDto {
    private Long id;
    private String titol;
    private LocalDate inici;
    private String comentaris;
    private Long treballId;

    public CalendariDto() {}

    public CalendariDto(Long id, String titol, LocalDate inici, String comentaris, Long treballId) {
        this.id = id;
        this.titol = titol;
        this.inici = inici;
        this.comentaris = comentaris;
        this.treballId = treballId;
    }

    // Constructor simplificat per a la vista de calendari
    public CalendariDto(LocalDate inici, String titol) {
        this.inici = inici;
        this.titol = titol;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitol() { return titol; }
    public void setTitol(String titol) { this.titol = titol; }
    public LocalDate getInici() { return inici; }
    public void setInici(LocalDate inici) { this.inici = inici; }
    public String getComentaris() { return comentaris; }
    public void setComentaris(String comentaris) { this.comentaris = comentaris; }
    public Long getTreballId() { return treballId; }
    public void setTreballId(Long treballId) { this.treballId = treballId; }
}
