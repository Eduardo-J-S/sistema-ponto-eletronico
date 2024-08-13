package com.ifpe.sistema_ponto_eletronico.service;
import org.springframework.stereotype.Service;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.RegistroPontoDTO;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;
import com.ifpe.sistema_ponto_eletronico.model.TipoRegistro;
import com.ifpe.sistema_ponto_eletronico.repository.RegistroPontoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RegistroPontoService {
    
    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @Autowired
    private ModelMapperConvert mapperConvert;

    @Transactional
    public RegistroPontoDTO create(RegistroPontoDTO registroPontoDTO){

        
        LocalDateTime dataHoraAtual = LocalDateTime.now().withSecond(0).withNano(0);
        LocalDate dataAtual = dataHoraAtual.toLocalDate();
        
        LocalDateTime inicioDoDia = dataAtual.atTime(LocalTime.MIN);
        LocalDateTime fimDoDia = dataAtual.atTime(LocalTime.MAX);
    
        List<RegistroPonto> registrosDoDia = registroPontoRepository.findByFuncionarioAndDataHoraBetween(registroPontoDTO.getFuncionario(), inicioDoDia, fimDoDia);

        boolean existeRegistroInicio = registrosDoDia.stream().anyMatch(registro -> registro.getTipoRegistro() == TipoRegistro.INICIO);
        boolean existeRegistroFim = registrosDoDia.stream().anyMatch(registro -> registro.getTipoRegistro() == TipoRegistro.FIM);

        // Verifica se o tipo de registro é INICIO
        if (registroPontoDTO.getTipoRegistro() == TipoRegistro.INICIO) {
            if (existeRegistroInicio) {
                throw new IllegalArgumentException("Já existe um registro de início!");
            }
        }

        // Verifica se o tipo de registro é FIM e se há um INICIO correspondente
        if (registroPontoDTO.getTipoRegistro() == TipoRegistro.FIM) {
            if (!existeRegistroInicio) {
                throw new IllegalArgumentException("Início NÃO registrado!");
            }
            if(existeRegistroFim){
                throw new IllegalArgumentException("Já existe um registro de fim no mesmo dia.");
            }
        }

        RegistroPonto registroPonto = mapperConvert.convertObject(registroPontoDTO, RegistroPonto.class);

        registroPonto.setDataHora(dataHoraAtual);

        return mapperConvert.convertObject(registroPontoRepository.save(registroPonto), RegistroPontoDTO.class);
        
    }

    public List<RegistroPonto> listarRegistrosPorFuncionario(Funcionario funcionario) {
        return registroPontoRepository.findByFuncionario(funcionario);
    }

    public List<RegistroPonto> listarRegistrosPorFuncionarioEIntervalo(Funcionario funcionario, LocalDateTime inicio, LocalDateTime fim) {
        return registroPontoRepository.findByFuncionarioAndDataHoraBetween(funcionario, inicio, fim);
    }
    
    
}
