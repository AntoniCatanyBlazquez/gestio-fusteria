package com.antoni.fusteria.service;

import com.antoni.fusteria.api.dto.CalendariDto;
import com.antoni.fusteria.api.dto.TreballDto;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.domain.repository.CalendariRepository;
import com.antoni.fusteria.domain.repository.TreballRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public List<CalendariDto> obtenirTreballsPerCalendari(){
        return calendariRepository.findAll().stream()
                .map(treball -> new CalendariDto(treball.getDateEntrada(),treball.getTitol()))
                .collect(Collectors.toList());
    }

    public TreballDto toDto(Treball treball) {
        return new TreballDto(
                treball.getId(),
                treball.getClient().getNom() + " " + treball.getClient().getLlinatge(),
                treball.getTitol(),
                treball.getDescripcio(),
                treball.getData().toString(),
                treball.getEstat(),
                treball.getPreu().doubleValue(),
                treball.getMaterials(),
                treball.getImatge()
        );
    }
}
