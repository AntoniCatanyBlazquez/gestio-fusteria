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
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDto> dtos = clients.stream()
                .map(clientService::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        ClientDto dto = clientService.toDto(client);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PatchMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/treballs")
    public ResponseEntity<List<Treball>> getTreballsByClientId (@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        List<Treball> treballs = client.getTreballs();
        return ResponseEntity.ok(treballs);
    }
}
