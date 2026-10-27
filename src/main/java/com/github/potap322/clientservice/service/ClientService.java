package com.github.potap322.clientservice.service;

import com.github.potap322.clientservice.exception.ClientNotFoundException;
import com.github.potap322.clientservice.model.Client;
import com.github.potap322.clientservice.model.ClientGender;
import com.github.potap322.clientservice.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client create(String fullName, ClientGender gender) {
        Client client = new Client(fullName, gender);

        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Transactional
    public Client update(Long id, String fullName, ClientGender gender) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));

        if (fullName != null) {
            client.setFullName(fullName);
        }
        if (gender != null) {
            client.setGender(gender);
        }

        return client;
    }

    @Transactional
    public void delete(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException(id);
        }

    }


}
