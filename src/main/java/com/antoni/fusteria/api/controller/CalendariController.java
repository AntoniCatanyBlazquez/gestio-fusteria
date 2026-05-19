package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.api.dto.CalendariDto;
import com.antoni.fusteria.domain.model.Calendari;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.CalendariService;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendaris")
public class CalendariController {

    private final CalendariService calendariService;
    private final TreballService treballService;

    @Autowired
    public CalendariController(CalendariService calendariService, TreballService treballService) {
        this.calendariService = calendariService;
        this.treballService = treballService;
    }

    @GetMapping
    public ResponseEntity<List<CalendariDto>> getAllCalendaris() {
        List<Calendari> calendaris = calendariService.getAllCalendaris();
        List<CalendariDto> dtos = calendaris.stream()
                .map(calendariService::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendariDto> getCalendariById(@PathVariable Long id) {
        Calendari calendari = calendariService.getCalendariById(id);
        if (calendari == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendariService.toDto(calendari));
    }

    @PostMapping
    public ResponseEntity<Calendari> createCalendari(@RequestBody Calendari calendari) {
        Calendari saved = calendariService.saveCalendari(calendari);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Calendari> updateCalendari(@PathVariable Long id, @RequestBody Calendari calendari) {
        Calendari existing = calendariService.getCalendariById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (calendari.getDateEntrada() != null) {
            existing.setDateEntrada(calendari.getDateEntrada());
        }
        if (calendari.getComentaris() != null) {
            existing.setComentaris(calendari.getComentaris());
        }

        return ResponseEntity.ok(calendariService.saveCalendari(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendari(@PathVariable Long id) {
        calendariService.deleteCalendari(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("treball/{id}/factures")
    public ResponseEntity<List<Factura>> getFacturesByTreballId(@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(treball.getFactures());
    }

    @GetMapping("/esdeveniments")
    public List<CalendariDto> obtenirEsdeveniment() {
        return treballService.obtenirTreballsPerCalendari();
    }
}
