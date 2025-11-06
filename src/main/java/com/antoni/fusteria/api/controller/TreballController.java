package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.api.dto.TreballDto;
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
    public ResponseEntity<List<TreballDto>> getAllTreballs(){
        List<Treball> treballs = treballService.getAllTreballs();
        List<TreballDto> dtos = treballs.stream()
                .map(treballService::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreballDto> getTreballById(@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) {
            return ResponseEntity.notFound().build();
        }
        TreballDto dto = treballService.toDto(treball);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Treball> createTreball(@RequestBody Treball treball){
        Treball savedTreball = treballService.saveTreball(treball);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTreball);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Treball> updateTreball(@PathVariable Long id, @RequestBody Treball treball) {
        Treball existingTreball = treballService.getTreballById(id);

        if (treball.getDescripcio() != null) {
            existingTreball.setDescripcio(treball.getDescripcio());
        }
        if (treball.getData() != null) {
            existingTreball.setData(treball.getData());
        }
        if (treball.getTitol() != null) {
            existingTreball.setTitol(treball.getTitol());
        }
        if (treball.getPreu() != null) {
            existingTreball.setPreu(treball.getPreu());
        }
        if (treball.getMaterials() != null) {
            existingTreball.setMaterials(treball.getMaterials());
        }
        if (treball.getEstat() != null) {
            existingTreball.setEstat(treball.getEstat());
        }
        if (existingTreball == null){
            return ResponseEntity.notFound().build();
        }

        Treball updatedTreball = treballService.saveTreball(existingTreball);
        return ResponseEntity.ok(updatedTreball);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreball(@PathVariable Long id) {
        treballService.deleteTreball(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/factures")
    public ResponseEntity<List<Factura>> getFacturesByTreballId (@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) {
            return ResponseEntity.notFound().build();
        }
        List<Factura> factures = treball.getFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<?> getClientByTreballId (@PathVariable Long id) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(treball.getClient());
    }
}
