package com.antoni.fusteria.service;

import com.antoni.fusteria.dto.CalendariTreballDto;
import com.antoni.fusteria.model.Treball;
import com.antoni.fusteria.repository.CalendariRepository;
import com.antoni.fusteria.repository.TreballRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TreballService {

    @Autowired
    private TreballRepository treballRepository;

    @Autowired
    private CalendariRepository calendariRepository;

    public List<Treball> getAllTreballs() {
        return treballRepository.findAll();
    }

    public Treball getTreballById(Long id) {
        return treballRepository.findById(id).orElse(null);
    }

    public Treball saveTreball(Treball treball) {
        return treballRepository.save(treball);
    }

    public void deleteTreball(Long id) {
        treballRepository.deleteById(id);
    }

    public List<CalendariTreballDto> obtenirTreballsPerCalendari(){
        return calendariRepository.findAll().stream()
                .map(treball -> new CalendariTreballDto(treball.getDateEntrada(),treball.getTitol()))
                .collect(Collectors.toList());
    }
}
