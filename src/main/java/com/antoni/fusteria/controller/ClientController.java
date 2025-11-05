package com.antoni.fusteria.controller;

import com.antoni.fusteria.model.Client;
import com.antoni.fusteria.model.Treball;
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
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PatchMapping("/api/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client existingClient = clientService.getClientById(id);

        if (client.getNom() != null) {
            existingClient.setNom(client.getNom());
        }
        if (client.getLlinatge() != null) {
            existingClient.setLlinatge(client.getLlinatge());
        }
        if (client.getTelefon() != 0) {
            existingClient.setTelefon(client.getTelefon());
        }
        if (client.getDireccio() != null) {
            existingClient.setDireccio(client.getDireccio());
        }
        if (client.getEmail() != null) {
            existingClient.setEmail(client.getEmail());
        }
        Client updatedClient = clientService.saveClient(existingClient);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/{id}/treballs")
    public ResponseEntity<List<Treball>> getTreballsByClientId (@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        List<Treball> treballs = client.getTreballs();
        return ResponseEntity.ok(treballs);
    }
}
