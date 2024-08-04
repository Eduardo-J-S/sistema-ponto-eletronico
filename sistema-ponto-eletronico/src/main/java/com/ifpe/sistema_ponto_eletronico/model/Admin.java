package com.ifpe.sistema_ponto_eletronico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    private String nomeAdmin;

    @Column(nullable = false)
    private String senhaAdmin;

    @Column(nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(unique = true, nullable = false)
    private String usuarioAdmin;

    @Column(unique = true, nullable = false)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}

