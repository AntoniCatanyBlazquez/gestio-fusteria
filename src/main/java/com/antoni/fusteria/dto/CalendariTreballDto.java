package com.antoni.fusteria.dto;

import java.time.LocalDate;

public class CalendariTreballDto {
    private String titol;
    private LocalDate inici;

    public CalendariTreballDto(LocalDate inici, String titol) {
        this.inici = inici;
        this.titol = titol;
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
