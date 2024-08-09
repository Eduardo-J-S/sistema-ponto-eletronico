package com.ifpe.sistema_ponto_eletronico.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.AusenciaDTO;
import com.ifpe.sistema_ponto_eletronico.model.Ausencia;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.repository.AusenciaRespository;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;

@Service
public class AusenciaService {
    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    @Autowired
    ModelMapperConvert mapperConvert;

    @Autowired
    AusenciaRespository ausenciaRespository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<AusenciaDTO> findAusenciasByFuncionarioCpf(Funcionario funcionarioCpf) {
        logger.info("Finding absences for employee with CPF: " + funcionarioCpf);

        Funcionario funcionario = funcionarioRepository.findByCpfOrMatricula(funcionarioCpf.getCpf())
            .orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrada! CPF: " + funcionarioCpf));

        List<Ausencia> ausencias = ausenciaRespository.findByFuncionario(funcionario);
        return mapperConvert.convertListObject(ausencias, AusenciaDTO.class);
    }


    @Transactional
    public AusenciaDTO create(AusenciaDTO ausenciaDTO){
        logger.info("Creating one absence!");

        Ausencia ausencia = mapperConvert.convertObject(ausenciaDTO, Ausencia.class);

        return mapperConvert.convertObject(ausenciaRespository.save(ausencia), AusenciaDTO.class);
    }

    @Transactional
    public AusenciaDTO update(AusenciaDTO ausenciaDTO){
        logger.info("Updating one absence!");

        Ausencia ausencia = ausenciaRespository.findById(ausenciaDTO.getIdAusencia()).orElseThrow(() -> 
            new ObjectNotFoundException( "Empresa não encontrada! Id: " + ausenciaDTO.getIdAusencia() + ", Tipo: " + Ausencia.class.getName()));

        ausencia.setTipoAusencia(ausenciaDTO.getTipoAusencia());
        ausencia.setDataInicio(ausenciaDTO.getDataInicio());
        ausencia.setDataFim(ausenciaDTO.getDataFim());
        ausencia.setFuncionario(ausenciaDTO.getFuncionario());
        
        return mapperConvert.convertObject(ausenciaRespository.save(ausencia), AusenciaDTO.class);
    }
    
}