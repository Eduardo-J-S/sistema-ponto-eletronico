package com.ifpe.sistema_ponto_eletronico.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.dto.FuncionarioDTO;
import com.ifpe.sistema_ponto_eletronico.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/funcionario/v1")
@Validated
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id){
        FuncionarioDTO FuncionarioDTO = funcionarioService.findById(id);
        return ResponseEntity.ok().body(FuncionarioDTO);
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        List<FuncionarioDTO> funcionarios = funcionarioService.findAll();
        return ResponseEntity.ok().body(funcionarios);
    }

    @PostMapping()
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO dto){
        FuncionarioDTO createdFuncionario = funcionarioService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFuncionario);
    }

    @PutMapping()
    public ResponseEntity<FuncionarioDTO> update(@Valid @RequestBody FuncionarioDTO dto) {
        FuncionarioDTO updatedFuncionario = funcionarioService.update(dto);
        return ResponseEntity.ok().body(updatedFuncionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Novo endpoint para atualizações parciais
    @PatchMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        FuncionarioDTO updatedFuncionario = funcionarioService.partialUpdate(id, updates);
        return ResponseEntity.ok().body(updatedFuncionario);
    }
}
