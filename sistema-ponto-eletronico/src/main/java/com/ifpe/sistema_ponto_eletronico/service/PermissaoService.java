package com.ifpe.sistema_ponto_eletronico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.PermissaoDTO;
import com.ifpe.sistema_ponto_eletronico.model.Permissao;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.repository.PermissaoRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;

public class PermissaoService {

    @Autowired
    ModelMapperConvert mapperConvert;

    @Autowired
    PermissaoRepository permissaoRespository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<PermissaoDTO> findPermissoesByFuncionarioCpf(Funcionario funcionario) {
        
        funcionario = funcionarioRepository.findByCpfOrMatricula(funcionario.getCpf())
            .orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrada! CPF: "));

        List<Permissao> permissoes = permissaoRespository.findByFuncionario(funcionario);
        return mapperConvert.convertListObject(permissoes, PermissaoDTO.class);
    }

    @Transactional
    public PermissaoDTO create(PermissaoDTO permissaoDTO){

        Permissao permissao = mapperConvert.convertObject(permissaoDTO, Permissao.class);

        return mapperConvert.convertObject(permissaoRespository.save(permissao), PermissaoDTO.class);
    }

    @Transactional
    public PermissaoDTO update(PermissaoDTO permissaoDTO){

        Permissao Permissao = permissaoRespository.findById(permissaoDTO.getIdPermissao()).orElseThrow(() -> 
            new ObjectNotFoundException( "Permissao não encontrada! Id: " + permissaoDTO.getIdPermissao() + ", Tipo: " + Permissao.class.getName()));

        Permissao.setTipoPermissao(permissaoDTO.getTipoPermissao());
        Permissao.setDataInicio(permissaoDTO.getDataInicio());
        Permissao.setDataFim(permissaoDTO.getDataFim());
        Permissao.setFuncionario(permissaoDTO.getFuncionario());
        
        return mapperConvert.convertObject(permissaoRespository.save(Permissao), PermissaoDTO.class);
    }

}
