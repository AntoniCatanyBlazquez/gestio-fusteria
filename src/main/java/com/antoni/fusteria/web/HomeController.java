package com.antoni.fusteria.web;

import com.antoni.fusteria.service.ClientService;
import com.antoni.fusteria.service.FacturaService;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired private ClientService clientService;
    @Autowired private TreballService treballService;
    @Autowired private FacturaService facturaService;

    @GetMapping("/")
    public String inici(Model model) {
        model.addAttribute("totalClients", clientService.getAllClients().size());
        model.addAttribute("totalTreballs", treballService.getAllTreballs().size());
        model.addAttribute("totalFactures", facturaService.getAllFactures().size());
        return "index";
    }
}
