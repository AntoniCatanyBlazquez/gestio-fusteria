package com.antoni.fusteria.web;

import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.model.IdentificacioClient;
import com.antoni.fusteria.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientWebController {

    @Autowired private ClientService clientService;

    @GetMapping
    public String llistat(@RequestParam(required = false) String query, Model model) {
        List<Client> clients = (query != null && !query.isBlank())
                ? clientService.searchClients(query)
                : clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("query", query);
        return "clients/llistat";
    }

    @GetMapping("/nou")
    public String nouForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("titolPagina", "Nou client");
        model.addAttribute("tipusIdentificacions", IdentificacioClient.values());
        return "clients/formulari";
    }

    @PostMapping("/nou")
    public String guardarNouClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        if (client == null) return "redirect:/clients";
        model.addAttribute("client", client);
        model.addAttribute("titolPagina", "Editar client");
        model.addAttribute("tipusIdentificacions", IdentificacioClient.values());
        return "clients/formulari";
    }

    @PostMapping("/{id}/editar")
    public String guardarClientEditat(@PathVariable Long id, @ModelAttribute Client client) {
        Client existing = clientService.getClientById(id);
        if (existing == null) return "redirect:/clients";
        existing.setNom(client.getNom());
        existing.setLlinatge(client.getLlinatge());
        existing.setTelefon(client.getTelefon());
        existing.setDireccio(client.getDireccio());
        existing.setEmail(client.getEmail());
        existing.setTipusIdentificacio(client.getTipusIdentificacio());
        existing.setNumeroIdentificacio(client.getNumeroIdentificacio());
        clientService.saveClient(existing);
        return "redirect:/clients";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
