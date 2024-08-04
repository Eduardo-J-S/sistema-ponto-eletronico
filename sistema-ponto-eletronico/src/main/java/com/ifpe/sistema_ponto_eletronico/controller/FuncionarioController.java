package com.ifpe.sistema_ponto_eletronico.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("api/funcionario/v1")
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/{id}")
    public FuncionarioDTO findById(@PathVariable Long id){
        return funcionarioService.findById(id);
    }

    @GetMapping()
    public List<FuncionarioDTO> findAll(){
        return funcionarioService.findAll();
    }

    @PostMapping()
    public FuncionarioDTO create(@RequestBody FuncionarioDTO dto){
        return funcionarioService.create(dto);
    }

    @PutMapping()
    public FuncionarioDTO update(@RequestBody FuncionarioDTO dto){
        return funcionarioService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        funcionarioService.delete(id);
    }

    // Novo endpoint para atualizações parciais
    @PatchMapping("/{id}")
    public FuncionarioDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return funcionarioService.partialUpdate(id, updates);
    }
}
