package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ausencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAusencia;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoAusencia;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    
    public Long getIdAusencia() {
        return idAusencia;
    }

    public void setIdAusencia(Long idAusencia) {
        this.idAusencia = idAusencia;
    }

    public TipoRegistro getTipoAusencia() {
        return tipoAusencia;
    }

    public void setTipoAusencia(TipoRegistro tipoAusencia) {
        this.tipoAusencia = tipoAusencia;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}

