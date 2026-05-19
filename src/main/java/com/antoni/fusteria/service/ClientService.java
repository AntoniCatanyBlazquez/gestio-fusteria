package com.antoni.fusteria.service;

import com.antoni.fusteria.api.dto.ClientDto;
import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> searchClients(String query) {
        String q = query.toLowerCase();
        List<Client> resultat = new ArrayList<>();
        for (Client c : clientRepository.findAll()) {
            boolean coincideixNom = c.getNom().toLowerCase().contains(q);
            boolean coincideixLlinatge = c.getLlinatge().toLowerCase().contains(q);
            boolean coincideixEmail = c.getEmail() != null && c.getEmail().toLowerCase().contains(q);
            boolean coincideixNif = c.getNumeroIdentificacio() != null && c.getNumeroIdentificacio().toLowerCase().contains(q);
            if (coincideixNom || coincideixLlinatge || coincideixEmail || coincideixNif) {
                resultat.add(c);
            }
        }
        return resultat;
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientDto toDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getNom(),
                client.getLlinatge(),
                client.getTelefon(),
                client.getDireccio(),
                client.getEmail()
        );
    }
}
