package com.ifpe.sistema_ponto_eletronico.dto;


public record FuncionarioLoginResponseDTO(Long id, String nome, String matricula, String role, String token){}