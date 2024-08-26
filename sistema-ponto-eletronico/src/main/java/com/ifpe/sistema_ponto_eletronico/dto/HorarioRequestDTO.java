package com.ifpe.sistema_ponto_eletronico.dto;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioRequestDTO {
    private String diaSemana;
    private String horaInicio;
    private String horaFim;
    private Funcionario funcionario;
}
