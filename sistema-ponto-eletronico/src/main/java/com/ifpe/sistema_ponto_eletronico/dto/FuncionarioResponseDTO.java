package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioResponseDTO  {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String matricula;
    private LocalDate dataNascimento;
    private String cpf;
    private String status;
}
