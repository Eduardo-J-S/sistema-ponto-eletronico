package com.ifpe.sistema_ponto_eletronico.service;

import java.util.logging.Logger;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.FuncionarioDTO;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.repository.EmpresaRepository;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.DataBindingViolationException;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {

    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ModelMapperConvert mapperConvert;

    @Autowired
    EmpresaRepository empresaRepository;
    
    public FuncionarioDTO findById(Long id) {
        logger.info("Finding one person!");

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> 
            new ObjectNotFoundException("Usuário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
 
        return mapperConvert.convertObject(funcionario, FuncionarioDTO.class);
    }

    public FuncionarioDTO findByCpfOrMatricula(String cpfOrMatricula){
        logger.info("Finding one person by CPF or Matricula!");

        Funcionario funcionario = funcionarioRepository.findByCpfOrMatricula(cpfOrMatricula)
            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! cpf ou matricula: " + cpfOrMatricula));

            return mapperConvert.convertObject(funcionario, FuncionarioDTO.class);
    }

    public List<FuncionarioDTO> findAll(){
        logger.info("Finding all people!");

        return mapperConvert.convertListObject(funcionarioRepository.findAll(), FuncionarioDTO.class); 
    }

    @Transactional //todas as operações de banco de dados realizadas dentro do escopo da transação são tratadas como uma única unidade de trabalho 
    public FuncionarioDTO create(FuncionarioDTO funcionarioDTO){
        logger.info("Creating one person!");

        // Verifica se a empresa existe
        Long empresaId = funcionarioDTO.getEmpresa().getId();
        Empresa empresa = empresaRepository.findById(empresaId)
             .orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada! Id: " + empresaId));

        Funcionario funcionario = mapperConvert.convertObject(funcionarioDTO, Funcionario.class);
        funcionario.setEmpresa(empresa);

        return mapperConvert.convertObject(funcionarioRepository.save(funcionario), FuncionarioDTO.class);
    }

    @Transactional
    public FuncionarioDTO update(FuncionarioDTO funcionarioDTO){
        logger.info("Updating one person!");

        Funcionario funcionario = funcionarioRepository.findById(funcionarioDTO.getId()).orElseThrow(() -> 
            new ObjectNotFoundException( "Usuário não encontrado! Id: " + funcionarioDTO.getId() + ", Tipo: " + Funcionario.class.getName()));

        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setMatricula(funcionarioDTO.getMatricula());
        funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setSenha(funcionarioDTO.getSenha());
        funcionario.setEmpresa(funcionario.getEmpresa());
        funcionario.setRegistrosPonto(funcionario.getRegistrosPonto());
        
        return mapperConvert.convertObject(funcionarioRepository.save(funcionario), FuncionarioDTO.class);
    }


    // Método para atualizações parciais
    @Transactional
    public FuncionarioDTO partialUpdate(Long id, Map<String, Object> updates) {
        logger.info("Partially updating one person!");
        
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));
    
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Funcionario.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, funcionario, value);
        });
    
        return mapperConvert.convertObject(funcionarioRepository.save(funcionario), FuncionarioDTO.class);
    }
    

    public void delete(Long id){
        logger.info("Deleting one person!");

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> 
        new ObjectNotFoundException( "Usuário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));

        try {
            funcionarioRepository.delete(funcionario);
        } catch (Exception e) {
           throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
        
    }

    //buscar funcionarios da empresa pelo cnpj
    public List<FuncionarioDTO> findFuncionariosByEmpresaCnpj(String empresaCnpj) {
        logger.info("Finding employees for company with CNPJ: " + empresaCnpj);

        Empresa empresa = empresaRepository.findByCnpj(empresaCnpj)
            .orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada! CNPJ: " + empresaCnpj));

        List<Funcionario> funcionarios = funcionarioRepository.findByEmpresa(empresa);
        return mapperConvert.convertListObject(funcionarios, FuncionarioDTO.class);
        
    }
}
