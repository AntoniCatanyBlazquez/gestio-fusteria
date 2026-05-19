package com.antoni.fusteria.web;

import com.antoni.fusteria.domain.model.Calendari;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.CalendariService;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/calendari")
public class CalendariWebController {

    @Autowired private CalendariService calendariService;
    @Autowired private TreballService treballService;

    @GetMapping
    public String llistat(Model model) {
        model.addAttribute("calendaris", calendariService.getAllCalendaris());
        return "calendari/llistat";
    }

    @GetMapping("/nou")
    public String nouForm(Model model) {
        model.addAttribute("treballs", treballService.getAllTreballs());
        return "calendari/formulari";
    }

    @PostMapping("/nou")
    public String guardarCalendari(
            @RequestParam Long treballId,
            @RequestParam String titol,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEntrada,
            @RequestParam(required = false) String comentaris) {
        Treball treball = treballService.getTreballById(treballId);
        Calendari calendari = new Calendari();
        calendari.setTreball(treball);
        calendari.setTitol(titol);
        calendari.setDateEntrada(dateEntrada);
        calendari.setComentaris(comentaris);
        calendariService.saveCalendari(calendari);
        return "redirect:/calendari";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarCalendari(@PathVariable Long id) {
        calendariService.deleteCalendari(id);
        return "redirect:/calendari";
    }
}
