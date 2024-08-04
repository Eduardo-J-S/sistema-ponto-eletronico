package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @Column(unique = true, nullable = false)
    private String matricula;

    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String status;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "funcionario")
    private Set<RegistroPonto> registrosPonto;

    @ManyToMany
    @JoinTable(
        name = "funcionario_horario",
        joinColumns = @JoinColumn(name = "funcionario_id"),
        inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private Set<Horario> horarios;

    @OneToMany(mappedBy = "funcionario")
    private Set<Ausencia> ausencias;

    @OneToMany(mappedBy = "funcionario")
    private Set<Permissao> permissoes;

}

