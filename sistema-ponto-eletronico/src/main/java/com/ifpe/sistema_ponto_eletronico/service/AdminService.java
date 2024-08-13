package com.ifpe.sistema_ponto_eletronico.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifpe.sistema_ponto_eletronico.convert.ModelMapperConvert;
import com.ifpe.sistema_ponto_eletronico.dto.AdminDTO;
import com.ifpe.sistema_ponto_eletronico.model.Admin;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.repository.AdminRepository;
import com.ifpe.sistema_ponto_eletronico.repository.EmpresaRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;


@Service
public class AdminService {

    private Logger logger = Logger.getLogger(AdminService.class.getName());

    @Autowired
    ModelMapperConvert mapperConvert;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    public AdminDTO findById(Long id){
        
        logger.info("Finding one admin!");

        Optional<Admin> admin = adminRepository.findById(id);
 
        return mapperConvert.convertObject(admin, AdminDTO.class);
    }

    public List<AdminDTO> findAll(){

        logger.info("Finding all admins!");

        return mapperConvert.convertListObject(adminRepository.findAll(), AdminDTO.class);
    }

    @Transactional
    public AdminDTO create (AdminDTO adminDTO){

        logger.info("Creating one admin!");

        Long empresaId = adminDTO.getEmpresa().getId();
        Empresa empresa = empresaRepository.findById(empresaId)
             .orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada! Id: " + empresaId));

        Admin admin = mapperConvert.convertObject(adminDTO, Admin.class);
        admin.setEmpresa(empresa);

        return mapperConvert.convertObject(adminRepository.save(admin), AdminDTO.class);
    }

    @Transactional
    public AdminDTO update(AdminDTO adminDTO){
        logger.info("Updating one admin!");

        Admin admin = adminRepository.findById(adminDTO.getIdAdmin()).orElseThrow(() -> 
            new ObjectNotFoundException( "Admin não encontrado! Id: " + adminDTO.getIdAdmin() + ", Tipo: " + Admin.class.getName()));

        admin.setNomeAdmin(adminDTO.getNomeAdmin());        
        admin.setMatricula(adminDTO.getMatricula());       
        admin.setSenhaAdmin(adminDTO.getSenhaAdmin());
        admin.setEmpresa(admin.getEmpresa());
        admin.setDataAlteracao(LocalDateTime.now());     
        
        return mapperConvert.convertObject(adminRepository.save(admin), AdminDTO.class);
    }

}
