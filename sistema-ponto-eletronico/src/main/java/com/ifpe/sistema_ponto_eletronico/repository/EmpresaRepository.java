package com.ifpe.sistema_ponto_eletronico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpe.sistema_ponto_eletronico.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    Optional<Empresa> findByCnpj(String cnpj);

    Optional<Empresa> findById(Long id);

    List<Empresa> findByNomeEmpresa(String nome);

    // busca por nome parcial
    List<Empresa> findByNomeEmpresaContainingIgnoreCase(String nome);
}
