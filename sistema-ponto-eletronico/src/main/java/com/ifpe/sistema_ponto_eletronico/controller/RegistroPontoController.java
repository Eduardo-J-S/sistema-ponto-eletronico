package com.ifpe.sistema_ponto_eletronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.dto.RegistroPontoDTO;
import com.ifpe.sistema_ponto_eletronico.service.RegistroPontoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/registroponto/v1")
@Validated
public class RegistroPontoController {

    @Autowired
    RegistroPontoService registroPontoService;

    @PostMapping()
    public ResponseEntity<RegistroPontoDTO> create(@Valid @RequestBody RegistroPontoDTO dto){
        RegistroPontoDTO createdRegistroPonto = registroPontoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRegistroPonto);
    }
}
