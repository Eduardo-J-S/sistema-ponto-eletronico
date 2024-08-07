package com.ifpe.sistema_ponto_eletronico.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpe.sistema_ponto_eletronico.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    Empresa findByCnpj(String cnpj);

    List<Empresa> findByNomeEmpresa(String nome);

    // busca por nome parcial
    List<Empresa> findByNomeEmpresaContainingIgnoreCase(String nome);
}
