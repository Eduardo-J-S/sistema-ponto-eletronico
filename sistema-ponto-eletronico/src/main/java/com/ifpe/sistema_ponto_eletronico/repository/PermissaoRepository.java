package com.ifpe.sistema_ponto_eletronico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    List<Permissao> findByFuncionario(Funcionario funcionario);


}
