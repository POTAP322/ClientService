package com.github.potap322.clientservice.controller;

import com.github.potap322.clientservice.dto.ClientCreateRequest;
import com.github.potap322.clientservice.dto.ClientResponse;
import com.github.potap322.clientservice.dto.ClientUpdateRequest;
import com.github.potap322.clientservice.model.Client;
import com.github.potap322.clientservice.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientCreateRequest request){
        Client client = clientService.create(request.fullName(), request.gender());

        return ResponseEntity.status(HttpStatus.CREATED).body(ClientResponse.from(client));

    }
    @GetMapping
    public List<ClientResponse> getAll(){
        List<Client> clientList = clientService.getAll();

        return clientList.stream().map(client -> ClientResponse.from(client)).toList();
    }

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id){
        Client client = clientService.getById(id);
        return ClientResponse.from(client);

    }

    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable Long id, @RequestBody ClientUpdateRequest request){
        Client updated = clientService.update(id, request.fullName(),request.gender());

        return ClientResponse.from(updated);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }

}
