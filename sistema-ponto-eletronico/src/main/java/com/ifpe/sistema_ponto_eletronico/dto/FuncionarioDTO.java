package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDate;
import java.util.Set;

import com.ifpe.sistema_ponto_eletronico.model.Ausencia;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.model.Horario;
import com.ifpe.sistema_ponto_eletronico.model.Permissao;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;

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
    private Set<RegistroPonto> registrosPonto;
    private Set<Permissao> permissoes;
    private Set<Ausencia> ausencias;
    private Set<Horario> horarios;

}
