package com.antoni.fusteria.web;

import com.antoni.fusteria.domain.model.Estat_Treball;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/estadistiques")
public class EstadistiquesWebController {

    @Autowired private TreballService treballService;

    @GetMapping
    public String resum(Model model) {
        List<Treball> tots = treballService.getAllTreballs();

        long pendents = 0, enCurs = 0, finalitzats = 0;
        BigDecimal ingressosTotals = BigDecimal.ZERO;
        Map<String, BigDecimal> perClient = new TreeMap<>();
        Map<String, BigDecimal> perMes = new TreeMap<>();

        for (Treball t : tots) {
            if (t.getEstat() == Estat_Treball.PENDENT) pendents++;
            else if (t.getEstat() == Estat_Treball.EN_CURS) enCurs++;
            else if (t.getEstat() == Estat_Treball.FINALITZAT) finalitzats++;

            if (t.getPreu() == null) continue;

            if (t.getEstat() == Estat_Treball.FINALITZAT) {
                ingressosTotals = ingressosTotals.add(t.getPreu());
            }
            if (t.getClient() != null) {
                String nom = t.getClient().getNom() + " " + t.getClient().getLlinatge();
                perClient.put(nom, perClient.getOrDefault(nom, BigDecimal.ZERO).add(t.getPreu()));
            }
            if (t.getData() != null) {
                String mes = t.getData().getYear() + "-" + String.format("%02d", t.getData().getMonthValue());
                perMes.put(mes, perMes.getOrDefault(mes, BigDecimal.ZERO).add(t.getPreu()));
            }
        }

        model.addAttribute("totalTreballs", tots.size());
        model.addAttribute("pendents", pendents);
        model.addAttribute("enCurs", enCurs);
        model.addAttribute("finalitzats", finalitzats);
        model.addAttribute("ingressosTotals", ingressosTotals);
        model.addAttribute("perClient", perClient);
        model.addAttribute("perMes", perMes);
        return "estadistiques/resum";
    }
}
