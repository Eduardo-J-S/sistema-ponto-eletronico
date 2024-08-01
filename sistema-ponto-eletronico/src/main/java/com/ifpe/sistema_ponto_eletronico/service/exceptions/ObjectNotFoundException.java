package com.ifpe.sistema_ponto_eletronico.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends EntityNotFoundException {

    public ObjectNotFoundException(String message){
        super(message);
    }
}