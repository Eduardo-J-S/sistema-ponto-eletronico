package com.ifpe.sistema_ponto_eletronico.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.ifpe.sistema_ponto_eletronico.model.Ausencia;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.model.Horario;
import com.ifpe.sistema_ponto_eletronico.model.Permissao;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;
import com.ifpe.sistema_ponto_eletronico.model.Turno;


public class FuncionarioDTO implements Serializable {

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
    private Set<Turno> turnos;
    private Set<Ausencia> ausencias;
    private Set<Horario> horarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<RegistroPonto> getRegistrosPonto() {
        return registrosPonto;
    }

    public void setRegistrosPonto(Set<RegistroPonto> registrosPonto) {
        this.registrosPonto = registrosPonto;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    public Set<Ausencia> getAusencias() {
        return ausencias;
    }

    public void setAusencias(Set<Ausencia> ausencias) {
        this.ausencias = ausencias;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }
}
