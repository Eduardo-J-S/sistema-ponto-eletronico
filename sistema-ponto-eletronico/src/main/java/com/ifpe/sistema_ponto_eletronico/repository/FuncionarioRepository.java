package com.ifpe.sistema_ponto_eletronico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    @Query("SELECT f FROM Funcionario f WHERE f.cpf = :cpfOrMatricula OR f.matricula = :cpfOrMatricula")
    Optional<Funcionario> findByCpfOrMatricula(@Param("cpfOrMatricula") String cpfOrMatricula);

    List<Funcionario> findByEmpresa(Empresa empresa);

    Funcionario findByCpfAndStatus(String cpf, String status);

    Optional<Funcionario> findByMatricula(String matricula);
}
