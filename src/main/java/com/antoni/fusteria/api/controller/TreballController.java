package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.api.dto.TreballDto;
import com.antoni.fusteria.domain.model.Estat_Treball;
import com.antoni.fusteria.domain.model.Factura;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treballs")
public class TreballController {

    private final TreballService treballService;

    @Autowired
    public TreballController(TreballService treballService) {
        this.treballService = treballService;
    }

    @GetMapping
    public ResponseEntity<List<TreballDto>> getAllTreballs(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Estat_Treball estat,
            @RequestParam(required = false) Long clientId) {

        List<Treball> treballs;
        if (query != null || estat != null || clientId != null) {
            treballs = treballService.searchTreballs(query, estat, clientId);
        } else {
            treballs = treballService.getAllTreballs();
        }
        return ResponseEntity.ok(treballs.stream().map(treballService::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreballDto> getTreballById(@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(treballService.toDto(treball));
    }

    @PostMapping
    public ResponseEntity<Treball> createTreball(@RequestBody Treball treball) {
        Treball saved = treballService.saveTreball(treball);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Treball> updateTreball(@PathVariable Long id, @RequestBody Treball treball) {
        Treball existing = treballService.getTreballById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        if (treball.getDescripcio() != null) existing.setDescripcio(treball.getDescripcio());
        if (treball.getData() != null) existing.setData(treball.getData());
        if (treball.getTitol() != null) existing.setTitol(treball.getTitol());
        if (treball.getPreu() != null) existing.setPreu(treball.getPreu());
        if (treball.getMaterials() != null) existing.setMaterials(treball.getMaterials());
        if (treball.getEstat() != null) existing.setEstat(treball.getEstat());
        if (treball.getImatge() != null) existing.setImatge(treball.getImatge());

        return ResponseEntity.ok(treballService.saveTreball(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreball(@PathVariable Long id) {
        treballService.deleteTreball(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/factures")
    public ResponseEntity<List<Factura>> getFacturesByTreballId(@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(treball.getFactures());
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<?> getClientByTreballId(@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(treball.getClient());
    }
}
