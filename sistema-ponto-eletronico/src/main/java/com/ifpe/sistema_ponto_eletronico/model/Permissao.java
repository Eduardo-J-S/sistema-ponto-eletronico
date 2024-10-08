package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import com.ifpe.sistema_ponto_eletronico.model.enumerable.TipoPermissao;

@Entity
@Getter
@Setter
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissao;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    private TipoPermissao tipoPermissao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;  
}

