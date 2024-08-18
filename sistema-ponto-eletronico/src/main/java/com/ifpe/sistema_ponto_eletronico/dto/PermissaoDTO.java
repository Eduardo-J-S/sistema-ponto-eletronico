package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDate;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.enumerable.TipoPermissao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDTO {

    private Long idPermissao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private TipoPermissao tipoPermissao;
    private Funcionario funcionario;

}
