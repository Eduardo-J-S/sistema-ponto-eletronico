package com.ifpe.sistema_ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpe.sistema_ponto_eletronico.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}
