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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.dto.HorarioDTO;
import com.ifpe.sistema_ponto_eletronico.service.HorarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/horario/v1")
@Validated
public class HorarioController {

    @Autowired
    HorarioService horarioService;

    @GetMapping(params = "cpfOrMatricula")
    public ResponseEntity<List<HorarioDTO>> findHorariosByFuncionarioCpf(@RequestParam("cpfOrMatricula") String cpf) {
        List<HorarioDTO> horarios = horarioService.findHorariosByFuncionarioCpf(cpf);
        return ResponseEntity.ok().body(horarios);
    }

    @PostMapping()
    public ResponseEntity<HorarioDTO> create(@Valid @RequestBody HorarioDTO dto){
        HorarioDTO createdHorario = horarioService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHorario);
    }

    @PutMapping()
    public ResponseEntity<HorarioDTO> update(@Valid @RequestBody HorarioDTO dto) {
        HorarioDTO updatedHorario = horarioService.update(dto);
        return ResponseEntity.ok().body(updatedHorario);
    }

}
