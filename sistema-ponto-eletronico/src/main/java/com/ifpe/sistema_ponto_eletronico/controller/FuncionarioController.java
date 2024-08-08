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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.FuncionarioDTO;
import com.ifpe.sistema_ponto_eletronico.dto.FuncionarioResponseDTO;
import com.ifpe.sistema_ponto_eletronico.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/funcionario/v1")
@Validated
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    ModelMapperConvert modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> findById(@PathVariable Long id){
        FuncionarioDTO FuncionarioDTO = funcionarioService.findById(id);
        FuncionarioResponseDTO funcionarioResponseDTO = modelMapper.convertObject(FuncionarioDTO, FuncionarioResponseDTO.class);
        return ResponseEntity.ok().body(funcionarioResponseDTO);
    }

    @GetMapping(params = "cpfOrMatricula")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioByCpfOrMatricula(@RequestParam("cpfOrMatricula") String cpfOrMatricula){
        FuncionarioDTO FuncionarioDTO = funcionarioService.findByCpfOrMatricula(cpfOrMatricula);
        FuncionarioResponseDTO funcionarioResponseDTO = modelMapper.convertObject(FuncionarioDTO, FuncionarioResponseDTO.class);
        return ResponseEntity.ok().body(funcionarioResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioResponseDTO>> findAll() {
        List<FuncionarioDTO> funcionarios = funcionarioService.findAll();
        List<FuncionarioResponseDTO> funcionarioResponseDTO = modelMapper.convertListObject(funcionarios, FuncionarioResponseDTO.class);
        return ResponseEntity.ok().body(funcionarioResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<FuncionarioResponseDTO> create(@Valid @RequestBody FuncionarioDTO dto){
        FuncionarioDTO createdFuncionario = funcionarioService.create(dto);
        FuncionarioResponseDTO funcionarioResponseDTO = modelMapper.convertObject(createdFuncionario, FuncionarioResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioResponseDTO);
    }

    @PutMapping()
    public ResponseEntity<FuncionarioResponseDTO> update(@Valid @RequestBody FuncionarioDTO dto) {
        FuncionarioDTO updatedFuncionario = funcionarioService.update(dto);
        FuncionarioResponseDTO funcionarioResponseDTO = modelMapper.convertObject(updatedFuncionario, FuncionarioResponseDTO.class);
        return ResponseEntity.ok().body(funcionarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Novo endpoint para atualizações parciais
    @PatchMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        FuncionarioDTO updatedFuncionario = funcionarioService.partialUpdate(id, updates);
        FuncionarioResponseDTO funcionarioResponseDTO = modelMapper.convertObject(updatedFuncionario, FuncionarioResponseDTO.class);
        return ResponseEntity.ok().body(funcionarioResponseDTO);
    }



    @GetMapping("/empresa/{empresaCnpj}")
    public ResponseEntity<List<FuncionarioResponseDTO>> getFuncionariosByEmpresaId(@PathVariable String empresaCnpj) {
        List<FuncionarioDTO> funcionarios = funcionarioService.findFuncionariosByEmpresaCnpj(empresaCnpj);
        List<FuncionarioResponseDTO> funcionarioResponseDTOs = modelMapper.convertListObject(funcionarios, FuncionarioResponseDTO.class);
        return ResponseEntity.ok().body(funcionarioResponseDTOs);
    }
}
