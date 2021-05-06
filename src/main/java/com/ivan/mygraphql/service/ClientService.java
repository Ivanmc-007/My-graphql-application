package com.ivan.mygraphql.service;

import com.ivan.mygraphql.model.entity.Client;
import com.ivan.mygraphql.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByName(String name) {
        return clientRepository.findByName(name);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }
}
