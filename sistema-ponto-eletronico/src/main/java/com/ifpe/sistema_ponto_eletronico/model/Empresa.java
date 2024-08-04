package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cnpj;

    private String nomeEmpresa;

    private String telefone;

    @OneToMany(mappedBy = "empresa")
    private Set<Funcionario> funcionarios;

    @OneToMany(mappedBy = "empresa")
    private Set<Admin> administradores;

    //@OneToMany(mappedBy = "empresa")
    //private Set<Feriado> feriados;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Set<Admin> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(Set<Admin> administradores) {
        this.administradores = administradores;
    }

    // public Set<Feriado> getFeriados() {
    //     return feriados;
    // }

    // public void setFeriados(Set<Feriado> feriados) {
    //     this.feriados = feriados;
    // }
}
