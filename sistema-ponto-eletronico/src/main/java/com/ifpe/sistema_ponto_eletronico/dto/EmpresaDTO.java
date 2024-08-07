package com.ifpe.sistema_ponto_eletronico.dto;

// import java.util.Set;


// import com.ifpe.sistema_ponto_eletronico.model.Admin;
// import com.ifpe.sistema_ponto_eletronico.model.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO {
    private Long id;
    private String cnpj;
    private String nomeEmpresa;
    private String telefone;
}
