package com.ifpe.sistema_ponto_eletronico.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;

import java.util.Set;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {

    //Esse método busca o último registro de ponto efetuado usando convenção do jpa
    RegistroPonto findTopByFuncionarioOrderByDataHoraDesc(Funcionario funcionario);

    List<RegistroPonto> findByFuncionario(Funcionario funcionario);

    List<RegistroPonto> findByFuncionarioAndDataHoraBetween(Funcionario funcionario, LocalDateTime inicio, LocalDateTime fim);

    List<RegistroPonto> findByFuncionarioAndDataHora(Funcionario funcionario, LocalDate data);

    List<RegistroPonto> findByFuncionarioCpfAndDataHoraBetweenAndFuncionarioStatus(
            String cpf, LocalDateTime dataInicio, LocalDateTime dataFim, String status);

    //Set<RegistroPonto> findByFuncionarioAndDataHoraBetween(Funcionario funcionario, LocalDateTime startDate, LocalDateTime endDate);

}
