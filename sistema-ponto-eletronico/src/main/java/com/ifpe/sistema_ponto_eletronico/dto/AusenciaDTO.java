package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDate;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.TipoRegistro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AusenciaDTO {
    private Long idAusencia;
    private TipoRegistro tipoAusencia;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Funcionario funcionario;
}
