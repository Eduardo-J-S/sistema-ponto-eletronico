package com.ifpe.sistema_ponto_eletronico.dto;

import java.time.LocalDateTime;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO {

    private Long idAdmin;
    private String nomeAdmin;
    private String senhaAdmin;
    private LocalDateTime dataAlteracao;
    private String usuarioAdmin;
    private String matricula;
    private Empresa empresa;

}
