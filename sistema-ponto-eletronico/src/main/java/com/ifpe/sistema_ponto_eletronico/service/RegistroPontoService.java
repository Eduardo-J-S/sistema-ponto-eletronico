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
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroPontoService {
    
    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @Autowired
    private ModelMapperConvert mapperConvert;

    @Transactional
    public RegistroPontoDTO create(RegistroPontoDTO registroPontoDTO){

        

        RegistroPonto ultimoRegistro = registroPontoRepository.findTopByFuncionarioOrderByDataHoraDesc(registroPontoDTO.getFuncionario());

        // Verifica se o tipo de registro é INICIO
        if (registroPontoDTO.getTipoRegistro() == TipoRegistro.INICIO) {
            if (ultimoRegistro != null && ultimoRegistro.getTipoRegistro() == TipoRegistro.INICIO) {
                throw new IllegalArgumentException("Já existe um registro de início!");
            }
        }

        // Verifica se o tipo de registro é FIM e se há um INICIO correspondente
        if (registroPontoDTO.getTipoRegistro() == TipoRegistro.FIM) {
            if (ultimoRegistro == null || ultimoRegistro.getTipoRegistro() != TipoRegistro.INICIO) {
                throw new IllegalArgumentException("Início NÃO registrado!");
            }
        }

        LocalDateTime dataHoraAtual = LocalDateTime.now().withSecond(0).withNano(0);

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
