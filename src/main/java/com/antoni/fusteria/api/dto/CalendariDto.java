package com.antoni.fusteria.api.dto;

import java.time.LocalDate;

public class CalendariDto {
    private String titol;
    private LocalDate inici;

    public CalendariDto(LocalDate inici, String titol) {
        this.inici = inici;
        this.titol = titol;
    }

    public CalendariDto(Long id, String titol, String string) {
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public LocalDate getInici() {
        return inici;
    }

    public void setInici(LocalDate inici) {
        this.inici = inici;
    }
}
