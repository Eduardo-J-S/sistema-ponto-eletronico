package com.ifpe.sistema_ponto_eletronico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.AusenciaDTO;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.service.AusenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/ausencia/v1")
@Validated
public class AusenciaController {

    @Autowired
    ModelMapperConvert modelMapper;

    @Autowired
    AusenciaService ausenciaService;

    
    @GetMapping()
    public ResponseEntity<List<AusenciaDTO>> findHorariosByFuncionarioCpf(@RequestBody Funcionario cpf) {
        List<AusenciaDTO> ausencias = ausenciaService.findAusenciasByFuncionarioCpf(cpf);
        return ResponseEntity.ok().body(ausencias);
    }

    @PostMapping()
    public ResponseEntity<AusenciaDTO> create(@Valid @RequestBody AusenciaDTO dto){
        AusenciaDTO createdAusencia = ausenciaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAusencia);
    }

    @PutMapping()
    public ResponseEntity<AusenciaDTO> update(@Valid @RequestBody AusenciaDTO dto) {
        AusenciaDTO updatedAusencia = ausenciaService.update(dto);
        return ResponseEntity.ok().body(updatedAusencia);
    }
}
