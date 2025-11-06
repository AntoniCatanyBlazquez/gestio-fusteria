package com.antoni.fusteria.service;

import com.antoni.fusteria.api.dto.CalendariDto;
import com.antoni.fusteria.api.dto.ClientDto;
import com.antoni.fusteria.domain.model.Calendari;
import com.antoni.fusteria.domain.repository.CalendariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public CalendariDto toDto(Calendari calendari) {
        return new CalendariDto(
                calendari.getId(),
                calendari.getTitol(),
                calendari.getDateEntrada().toString()
        );
    }
}
