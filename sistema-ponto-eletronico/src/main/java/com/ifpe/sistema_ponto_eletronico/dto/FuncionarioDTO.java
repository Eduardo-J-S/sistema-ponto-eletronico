package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDate;

import com.ifpe.sistema_ponto_eletronico.model.Empresa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String matricula;
    private LocalDate dataNascimento;
    private String cpf;
    private String status;
    private String senha;
    private Empresa empresa;

}
