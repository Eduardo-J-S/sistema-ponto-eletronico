package com.ifpe.sistema_ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {

}
