package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.domain.model.Estat_Treball;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/estadistiques")
public class EstadistiquesController {

    @Autowired
    private TreballService treballService;

    @GetMapping("/resum")
    public ResponseEntity<Map<String, Object>> getResum() {
        List<Treball> tots = treballService.getAllTreballs();

        long pendents = 0;
        long enCurs = 0;
        long finalitzats = 0;
        BigDecimal ingressosTotals = BigDecimal.ZERO;
        Map<String, BigDecimal> ingressosPerClient = new TreeMap<>();
        Map<String, BigDecimal> ingressosPerMes = new TreeMap<>();

        for (Treball t : tots) {
            if (t.getEstat() == Estat_Treball.PENDENT) pendents++;
            else if (t.getEstat() == Estat_Treball.EN_CURS) enCurs++;
            else if (t.getEstat() == Estat_Treball.FINALITZAT) finalitzats++;

            if (t.getPreu() == null) continue;

            if (t.getEstat() == Estat_Treball.FINALITZAT) {
                ingressosTotals = ingressosTotals.add(t.getPreu());
            }

            if (t.getClient() != null) {
                String nomClient = t.getClient().getNom() + " " + t.getClient().getLlinatge();
                BigDecimal actual = ingressosPerClient.getOrDefault(nomClient, BigDecimal.ZERO);
                ingressosPerClient.put(nomClient, actual.add(t.getPreu()));
            }

            if (t.getData() != null) {
                String mes = t.getData().getYear() + "-" + String.format("%02d", t.getData().getMonthValue());
                BigDecimal actual = ingressosPerMes.getOrDefault(mes, BigDecimal.ZERO);
                ingressosPerMes.put(mes, actual.add(t.getPreu()));
            }
        }

        Map<String, Object> resum = new LinkedHashMap<>();
        resum.put("totalTreballs", tots.size());
        resum.put("pendents", pendents);
        resum.put("enCurs", enCurs);
        resum.put("finalitzats", finalitzats);
        resum.put("ingressosTotals", ingressosTotals);
        resum.put("ingressosPerClient", ingressosPerClient);
        resum.put("ingressosPerMes", ingressosPerMes);

        return ResponseEntity.ok(resum);
    }
}
