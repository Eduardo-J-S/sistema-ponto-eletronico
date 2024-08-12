package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    private String telefone;

    @Email(message = "Email deve ser válido")
    private String email;

    @Column(unique = true, nullable = false)
    @NotNull(message = "A matricula não pode ser nula")
    private String matricula;

    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false)
    //@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    @NotNull(message = "CPF é obrigatório")
    private String cpf;

    private String status;

    @JsonIgnore // Para evitar expor a senha
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference
    private Empresa empresa;

    @JsonIgnore // Para evitar referência circular
    @OneToMany(mappedBy = "funcionario")
    private Set<RegistroPonto> registrosPonto;

    @JsonIgnore // Para evitar referência circular
    @ManyToMany
    @JoinTable(
        name = "funcionario_horario",
        joinColumns = @JoinColumn(name = "funcionario_id"),
        inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private Set<Horario> horarios;

    @JsonIgnore // Para evitar referência circular
    @OneToMany(mappedBy = "funcionario")
    private Set<Ausencia> ausencias;

    @JsonIgnore // Para evitar referência circular
    @OneToMany(mappedBy = "funcionario")
    private Set<Permissao> permissoes;

}

