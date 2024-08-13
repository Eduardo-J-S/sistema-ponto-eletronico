package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RegistroPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    // depois testar sem esse atributo
    @Enumerated(EnumType.STRING)
    private DayOfWeek diaSemana;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;

}

