package com.ifpe.sistema_ponto_eletronico.service;
import org.springframework.stereotype.Service;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;
import com.ifpe.sistema_ponto_eletronico.model.TipoRegistro;
import com.ifpe.sistema_ponto_eletronico.repository.RegistroPontoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroPontoService {
    
    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    public void create(Funcionario funcionario, TipoRegistro tipoRegistro){

        

        RegistroPonto ultimoRegistro = registroPontoRepository.findTopByFuncionarioOrderByDataHoraDesc(funcionario);

        // Verifica se o tipo de registro é INICIO
        if (tipoRegistro == TipoRegistro.INICIO) {
            if (ultimoRegistro != null && ultimoRegistro.getTipoRegistro() == TipoRegistro.INICIO) {
                throw new IllegalArgumentException("Já existe um registro de início!");
            }
        }

        // Verifica se o tipo de registro é FIM e se há um INICIO correspondente
        if (tipoRegistro == TipoRegistro.FIM) {
            if (ultimoRegistro == null || ultimoRegistro.getTipoRegistro() != TipoRegistro.INICIO) {
                throw new IllegalArgumentException("Início NÃO registrado!");
            }
        }

        LocalDateTime dataHoraAtual = LocalDateTime.now().withSecond(0).withNano(0);

        // Cria e salva o novo registro de ponto
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setDataHora(dataHoraAtual);
        registroPonto.setFuncionario(funcionario);
        registroPonto.setTipoRegistro(tipoRegistro);

        registroPontoRepository.save(registroPonto);
    }

    public List<RegistroPonto> listarRegistrosPorFuncionario(Funcionario funcionario) {
        return registroPontoRepository.findByFuncionario(funcionario);
    }

    public List<RegistroPonto> listarRegistrosPorFuncionarioEIntervalo(Funcionario funcionario, LocalDateTime inicio, LocalDateTime fim) {
        return registroPontoRepository.findByFuncionarioAndDataHoraBetween(funcionario, inicio, fim);
    }
    

}
