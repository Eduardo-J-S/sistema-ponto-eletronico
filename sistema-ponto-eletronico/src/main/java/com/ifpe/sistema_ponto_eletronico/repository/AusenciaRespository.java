package com.ifpe.sistema_ponto_eletronico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpe.sistema_ponto_eletronico.model.Ausencia;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;

public interface AusenciaRespository extends JpaRepository<Ausencia, Long> {
    
    List<Ausencia> findByFuncionario(Funcionario ausencia);
}
