package com.antoni.fusteria.service;

import com.antoni.fusteria.api.dto.CalendariDto;
import com.antoni.fusteria.api.dto.TreballDto;
import com.antoni.fusteria.domain.model.Estat_Treball;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.domain.repository.CalendariRepository;
import com.antoni.fusteria.domain.repository.TreballRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Treball> searchTreballs(String query, Estat_Treball estat, Long clientId) {
        List<Treball> resultat = new ArrayList<>();
        for (Treball t : treballRepository.findAll()) {
            if (query != null) {
                String q = query.toLowerCase();
                boolean teTitol = t.getTitol().toLowerCase().contains(q);
                boolean teDescripcio = t.getDescripcio() != null && t.getDescripcio().toLowerCase().contains(q);
                boolean teMaterials = t.getMaterials() != null && t.getMaterials().toLowerCase().contains(q);
                if (!teTitol && !teDescripcio && !teMaterials) continue;
            }
            if (estat != null && t.getEstat() != estat) continue;
            if (clientId != null && (t.getClient() == null || !t.getClient().getId().equals(clientId))) continue;
            resultat.add(t);
        }
        return resultat;
    }

    public List<CalendariDto> obtenirTreballsPerCalendari() {
        return calendariRepository.findAll().stream()
                .map(c -> new CalendariDto(
                        c.getId(),
                        c.getTitol(),
                        c.getDateEntrada(),
                        c.getComentaris(),
                        c.getTreball() != null ? c.getTreball().getId() : null
                ))
                .collect(Collectors.toList());
    }

    public TreballDto toDto(Treball treball) {
        return new TreballDto(
                treball.getId(),
                treball.getClient() != null
                        ? treball.getClient().getNom() + " " + treball.getClient().getLlinatge()
                        : "",
                treball.getTitol(),
                treball.getDescripcio(),
                treball.getData() != null ? treball.getData().toString() : null,
                treball.getEstat(),
                treball.getPreu() != null ? treball.getPreu().doubleValue() : 0.0,
                treball.getMaterials(),
                treball.getImatge()
        );
    }
}
