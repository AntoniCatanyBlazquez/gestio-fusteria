package com.antoni.fusteria.controller;

import com.antoni.fusteria.dto.CalendariTreballDto;
import com.antoni.fusteria.model.Calendari;
import com.antoni.fusteria.model.Factura;
import com.antoni.fusteria.model.Treball;
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
    public ResponseEntity<List<Calendari>> getAllCalendaris() {
        List<Calendari> calendaris = calendariService.getAllCalendaris();
        return ResponseEntity.ok(calendaris);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendari> getCalendariById(@PathVariable Long id){
        Calendari calendari = calendariService.getCalendariById(id);
        if (calendari == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendari);
    }

    @PostMapping
    public ResponseEntity<Calendari> createCalendari(@RequestBody Calendari calendari){
        Calendari savedCalendari = calendariService.saveCalendari(calendari);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCalendari);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Calendari> updateCalendari(@PathVariable Long id, @RequestBody Calendari calendari) {
        Calendari existingCalendari = calendariService.getCalendariById(id);

        if (calendari.getDateEntrada() != null) {
            existingCalendari.setDateEntrada(calendari.getDateEntrada());
        }
        if (calendari.getComentaris() != null) {
            existingCalendari.setComentaris(calendari.getComentaris());
        }
        if (existingCalendari == null) {
            return ResponseEntity.notFound().build();
        }

        Calendari updatedCalendari = calendariService.saveCalendari(existingCalendari);
        return ResponseEntity.ok(updatedCalendari);
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
        List<Factura> factures = treball.getFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/eventos")
    public List<CalendariTreballDto> obtenirEsdeveniment() {
        return treballService.obtenirTreballsPerCalendari();
    }

}
