package com.antoni.fusteria.api.controller;

import com.antoni.fusteria.api.dto.ClientDto;
import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(
            @RequestParam(required = false) String query) {
        List<Client> clients = query != null
                ? clientService.searchClients(query)
                : clientService.getAllClients();
        return ResponseEntity.ok(clients.stream().map(clientService::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clientService.toDto(client));
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client saved = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client existing = clientService.getClientById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        if (client.getNom() != null) existing.setNom(client.getNom());
        if (client.getLlinatge() != null) existing.setLlinatge(client.getLlinatge());
        if (client.getTelefon() != null) existing.setTelefon(client.getTelefon());
        if (client.getDireccio() != null) existing.setDireccio(client.getDireccio());
        if (client.getEmail() != null) existing.setEmail(client.getEmail());

        return ResponseEntity.ok(clientService.saveClient(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/treballs")
    public ResponseEntity<List<Treball>> getTreballsByClientId(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(client.getTreballs());
    }
}
