package com.antoni.fusteria.service;

import com.antoni.fusteria.model.Calendari;
import com.antoni.fusteria.repository.CalendariRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CalendariService {

    @Autowired
    private CalendariRepository calendariRepository;

    public List<Calendari> getAllCalendaris() {
        return calendariRepository.findAll();
    }

    public Calendari getCalendariById(Long id) {
        return calendariRepository.findById(id).orElse(null);
    }

    public Calendari saveCalendari(Calendari calendari) {
        return calendariRepository.save(calendari);
    }

    public void deleteCalendari(Long id) {
        calendariRepository.deleteById(id);
    }

}
