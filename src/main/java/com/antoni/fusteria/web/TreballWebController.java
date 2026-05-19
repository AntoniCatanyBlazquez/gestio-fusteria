package com.antoni.fusteria.web;

import com.antoni.fusteria.domain.model.Estat_Treball;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.ClientService;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/treballs")
public class TreballWebController {

    @Autowired private TreballService treballService;
    @Autowired private ClientService clientService;

    @GetMapping
    public String llistat(@RequestParam(required = false) String query,
                          @RequestParam(required = false) Estat_Treball estat,
                          Model model) {
        List<Treball> treballs = (query != null || estat != null)
                ? treballService.searchTreballs(query, estat, null)
                : treballService.getAllTreballs();
        model.addAttribute("treballs", treballs);
        model.addAttribute("estats", Estat_Treball.values());
        model.addAttribute("query", query);
        model.addAttribute("estatSeleccionat", estat);
        return "treballs/llistat";
    }

    @GetMapping("/nou")
    public String nouForm(Model model) {
        model.addAttribute("treball", new Treball());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("estats", Estat_Treball.values());
        model.addAttribute("titolPagina", "Nou treball");
        return "treballs/formulari";
    }

    @PostMapping("/nou")
    public String guardarNouTreball(
            @RequestParam String titol,
            @RequestParam(required = false) String descripcio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Estat_Treball estat,
            @RequestParam(required = false) BigDecimal preu,
            @RequestParam(required = false) String materials,
            @RequestParam(required = false) Long clientId) {
        Treball treball = new Treball();
        treball.setTitol(titol);
        treball.setDescripcio(descripcio);
        treball.setData(data);
        treball.setEstat(estat != null ? estat : Estat_Treball.PENDENT);
        treball.setPreu(preu);
        treball.setMaterials(materials);
        if (clientId != null) treball.setClient(clientService.getClientById(clientId));
        treballService.saveTreball(treball);
        return "redirect:/treballs";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        Treball treball = treballService.getTreballById(id);
        if (treball == null) return "redirect:/treballs";
        model.addAttribute("treball", treball);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("estats", Estat_Treball.values());
        model.addAttribute("titolPagina", "Editar treball");
        return "treballs/formulari";
    }

    @PostMapping("/{id}/editar")
    public String guardarTreballEditat(
            @PathVariable Long id,
            @RequestParam String titol,
            @RequestParam(required = false) String descripcio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Estat_Treball estat,
            @RequestParam(required = false) BigDecimal preu,
            @RequestParam(required = false) String materials,
            @RequestParam(required = false) Long clientId) {
        Treball existing = treballService.getTreballById(id);
        if (existing == null) return "redirect:/treballs";
        existing.setTitol(titol);
        existing.setDescripcio(descripcio);
        existing.setData(data);
        if (estat != null) existing.setEstat(estat);
        existing.setPreu(preu);
        existing.setMaterials(materials);
        if (clientId != null) existing.setClient(clientService.getClientById(clientId));
        treballService.saveTreball(existing);
        return "redirect:/treballs";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarTreball(@PathVariable Long id) {
        treballService.deleteTreball(id);
        return "redirect:/treballs";
    }
}
