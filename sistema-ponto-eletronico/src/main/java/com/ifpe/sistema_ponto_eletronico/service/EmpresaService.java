package com.ifpe.sistema_ponto_eletronico.service;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.EmpresaDTO;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.repository.EmpresaRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;

@Service
public class EmpresaService {

    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ModelMapperConvert mapperConvert;

    public List<EmpresaDTO> findAll(){
        logger.info("Finding all companies!");

        return mapperConvert.convertListObject(empresaRepository.findAll(), EmpresaDTO.class); 
    }

    public EmpresaDTO findByCnpj(String cnpj){
        logger.info("Finding one company by CNPJ!");

        return mapperConvert.convertObject(empresaRepository.findByCnpj(cnpj), EmpresaDTO.class); 
    }

    public List<EmpresaDTO> findByNome(String nome){
        logger.info("Finding one company by name!");

        return mapperConvert.convertListObject(empresaRepository.findByNomeEmpresa(nome), EmpresaDTO.class); 
    }

    // buscar empresa com nome parcial
    public List<EmpresaDTO> findByNomeEmpresaContainingIgnoreCase(String nome){
        logger.info("Finding one company by partial name!");

        return mapperConvert.convertListObject(empresaRepository.findByNomeEmpresaContainingIgnoreCase(nome), EmpresaDTO.class); 
    }

    @Transactional //todas as operações de banco de dados realizadas dentro do escopo da transação são tratadas como uma única unidade de trabalho 
    public EmpresaDTO create(EmpresaDTO empresaDTO){
        logger.info("Creating one company!");

        Empresa empresa = mapperConvert.convertObject(empresaDTO, Empresa.class);

        return mapperConvert.convertObject(empresaRepository.save(empresa), EmpresaDTO.class);
    }

    @Transactional
    public EmpresaDTO update(EmpresaDTO empresaDTO){
        logger.info("Updating one company!");

        Empresa empresa = empresaRepository.findById(empresaDTO.getId()).orElseThrow(() -> 
            new ObjectNotFoundException( "Empresa não encontrada! Id: " + empresaDTO.getId() + ", Tipo: " + Empresa.class.getName()));

        empresa.setNomeEmpresa(empresaDTO.getNomeEmpresa());
        empresa.setTelefone(empresaDTO.getTelefone());
        empresa.setCnpj(empresaDTO.getCnpj());
        
        return mapperConvert.convertObject(empresaRepository.save(empresa), EmpresaDTO.class);
    }
}
