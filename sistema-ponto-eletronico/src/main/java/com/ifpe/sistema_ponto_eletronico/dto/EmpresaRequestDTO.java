package com.ifpe.sistema_ponto_eletronico.dto;

public record EmpresaRequestDTO(
        String nomeEmpresa,
        String cnpj,
        String telefone,
        String nomeAdmin,
        String matriculaAdmin,
        String senhaAdmin,
        String cpfAdmin,
        String emailAdmin) {
}