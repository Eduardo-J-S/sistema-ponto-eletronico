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
import com.ifpe.sistema_ponto_eletronico.dto.PermissaoDTO;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.service.PermissaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/permissao/v1")
@Validated
public class PermissaoController {

    @Autowired
    ModelMapperConvert modelMapper;

    @Autowired
    PermissaoService permissaoService;

    @GetMapping()
    public ResponseEntity<List<PermissaoDTO>> findPermissoesByFuncionarioCpf(@RequestBody Funcionario funcionario) {
        List<PermissaoDTO> permissoes = permissaoService.findPermissoesByFuncionarioCpf(funcionario);
        return ResponseEntity.ok().body(permissoes);
    }

    @PostMapping()
    public ResponseEntity<PermissaoDTO> create(@Valid @RequestBody PermissaoDTO dto){
        PermissaoDTO createdPermissao = permissaoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermissao);
    }

    @PutMapping()
    public ResponseEntity<PermissaoDTO> update(@Valid @RequestBody PermissaoDTO dto) {
        PermissaoDTO updatedPermissao = permissaoService.update(dto);
        return ResponseEntity.ok().body(updatedPermissao);
    }
}
