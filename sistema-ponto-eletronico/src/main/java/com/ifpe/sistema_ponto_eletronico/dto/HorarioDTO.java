package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioDTO {
    private Long id;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Funcionario funcionario;
}
