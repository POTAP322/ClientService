package com.github.potap322.clientservice.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long id){
        super("Client not found with id:  " + id);
    }
}
