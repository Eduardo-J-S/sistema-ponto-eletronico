package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDateTime;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.enumerable.TipoRegistro;

import java.time.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroPontoDTO {
    private Long id;
    private LocalDateTime dataHora;
    private DayOfWeek diaSemana;
    private Funcionario funcionario;
    private TipoRegistro tipoRegistro;
}
