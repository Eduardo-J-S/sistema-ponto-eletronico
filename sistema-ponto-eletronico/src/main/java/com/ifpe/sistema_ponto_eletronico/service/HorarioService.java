package com.ifpe.sistema_ponto_eletronico.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.HorarioDTO;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.Horario;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import com.ifpe.sistema_ponto_eletronico.repository.HorarioRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.DataBindingViolationException;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;

@Service
public class HorarioService {
     private Logger logger = Logger.getLogger(FuncionarioService.class.getName());
    
    @Autowired
    ModelMapperConvert mapperConvert;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<HorarioDTO> findHorariosByFuncionarioCpf(String funcionarioCpf) {
        logger.info("Finding hours for employee with CPF: " + funcionarioCpf);

        Funcionario funcionario = funcionarioRepository.findByCpfOrMatricula(funcionarioCpf)
            .orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrada! CPF: " + funcionarioCpf));

        List<Horario> horarios = horarioRepository.findByFuncionario(funcionario);
        return mapperConvert.convertListObject(horarios, HorarioDTO.class);
    }

    @Transactional
    public HorarioDTO create(HorarioDTO horarioDTO){
        logger.info("Creating one hour!");

        Funcionario funcionario = funcionarioRepository.findById(horarioDTO.getFuncionario().getId())
        .orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrada! CPF: " + horarioDTO.getFuncionario().getCpf()));

        Horario horario = mapperConvert.convertObject(horarioDTO, Horario.class);

        HorarioDTO horarioSave = mapperConvert.convertObject(horarioRepository.save(horario), HorarioDTO.class);

        funcionario.getHorarios().add(horario);

        funcionarioRepository.save(funcionario);

        return horarioSave;
    }

    @Transactional
    public HorarioDTO update(HorarioDTO horarioDTO){
        logger.info("Updating one company!");

        Horario horario = horarioRepository.findById(horarioDTO.getId()).orElseThrow(() -> 
            new ObjectNotFoundException( "Empresa não encontrada! Id: " + horarioDTO.getId() + ", Tipo: " + Horario.class.getName()));

        horario.setDiaSemana(horarioDTO.getDiaSemana());
        horario.setHoraInicio(horarioDTO.getHoraInicio());
        horario.setHoraFim(horarioDTO.getHoraFim());
        horario.setFuncionario(horarioDTO.getFuncionario());
        
        return mapperConvert.convertObject(horarioRepository.save(horario), HorarioDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one hour!");
    
        // Busca o horário pelo ID ou lança uma exceção se não encontrado
        Horario horario = horarioRepository.findById(id).orElseThrow(() -> 
            new ObjectNotFoundException("Horário não encontrado! Id: " + id + ", Tipo: " + Horario.class.getName()));
    
        // Desassocia o horário do funcionário e limpa os campos
        horario.getFuncionario().getHorarios().removeIf(h -> h.getId().equals(horario.getId()));
    
        try {
            // Exclui o horário do repositório
            horarioRepository.delete(horario);
            logger.info("Hour deleted successfully!");
        } catch (Exception e) {
            // Trata a exceção em caso de falha na exclusão
            throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
