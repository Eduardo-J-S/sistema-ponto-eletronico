package com.ifpe.sistema_ponto_eletronico.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.dto.EmpresaDTO;
import com.ifpe.sistema_ponto_eletronico.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/empresa/v1")
@Validated
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping()
    public ResponseEntity<List<EmpresaDTO>> findAll() {
        List<EmpresaDTO> empresas = empresaService.findAll();
        return ResponseEntity.ok().body(empresas);
    }
    
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaDTO> findByCnpj(@PathVariable String cnpj){
        EmpresaDTO empresaDTO = empresaService.findByCnpj(cnpj);
        return ResponseEntity.ok().body(empresaDTO);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<EmpresaDTO>> findByNome(@PathVariable String nome){
        List<EmpresaDTO> empresaDTO = empresaService.findByNome(nome);
        return ResponseEntity.ok().body(empresaDTO);
    }

    @GetMapping("/nomeparcial/{nome}")
    public ResponseEntity<List<EmpresaDTO>> findByNomeEmpresaContainingIgnoreCase(@PathVariable String nome){
        List<EmpresaDTO> empresaDTO = empresaService.findByNomeEmpresaContainingIgnoreCase(nome);
        return ResponseEntity.ok().body(empresaDTO);
    }

    @PostMapping()
    public ResponseEntity<EmpresaDTO> create(@Valid @RequestBody EmpresaDTO dto){
        EmpresaDTO createdEmpresa = empresaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpresa);
    }

    @PutMapping()
    public ResponseEntity<EmpresaDTO> update(@Valid @RequestBody EmpresaDTO dto) {
        EmpresaDTO updatedEmpresa = empresaService.update(dto);
        return ResponseEntity.ok().body(updatedEmpresa);
    }

}
