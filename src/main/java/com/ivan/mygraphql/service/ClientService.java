package com.ivan.mygraphql.service;

import com.ivan.mygraphql.model.entity.Client;
import com.ivan.mygraphql.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByName(String name) {
        return clientRepository.findByName(name);
    }
}
