package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cnpj;

    private String nomeEmpresa;

    private String telefone;

    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference
    private Set<Funcionario> funcionarios;

    @OneToMany(mappedBy = "empresa")
    private Set<Admin> administradores;

    //@OneToMany(mappedBy = "empresa")
    //private Set<Feriado> feriados;

}
