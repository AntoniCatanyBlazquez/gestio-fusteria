package com.antoni.fusteria.service;

import com.antoni.fusteria.domain.model.Client;
import com.antoni.fusteria.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void cercaPerNomRetornaClientCorrecte() {
        Client client1 = new Client();
        client1.setNom("Joan");
        client1.setLlinatge("Garcia");
        client1.setEmail("joan@email.com");

        Client client2 = new Client();
        client2.setNom("Maria");
        client2.setLlinatge("López");
        client2.setEmail("maria@email.com");

        when(clientRepository.findAll()).thenReturn(List.of(client1, client2));

        List<Client> resultat = clientService.searchClients("joan");

        assertEquals(1, resultat.size());
        assertEquals("Joan", resultat.get(0).getNom());
    }

    @Test
    void cercaPerEmailRetornaClientCorrecte() {
        Client client = new Client();
        client.setNom("Pere");
        client.setLlinatge("Mas");
        client.setEmail("pere@fusteria.com");

        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> resultat = clientService.searchClients("fusteria");

        assertEquals(1, resultat.size());
        assertEquals("Pere", resultat.get(0).getNom());
    }

    @Test
    void cercaSenseCoincidenciesRetornaLlistatBuit() {
        Client client = new Client();
        client.setNom("Joan");
        client.setLlinatge("Garcia");

        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> resultat = clientService.searchClients("zzz");

        assertTrue(resultat.isEmpty());
    }

    @Test
    void cercaPerNifRetornaClientCorrecte() {
        Client client = new Client();
        client.setNom("Anna");
        client.setLlinatge("Pons");
        client.setNumeroIdentificacio("12345678A");

        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> resultat = clientService.searchClients("12345678A");

        assertEquals(1, resultat.size());
        assertEquals("Anna", resultat.get(0).getNom());
    }
}
