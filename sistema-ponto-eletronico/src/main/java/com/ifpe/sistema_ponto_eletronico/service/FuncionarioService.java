package com.ifpe.sistema_ponto_eletronico.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    @Autowired
    FuncionarioRepository funcionarioRepository;
    
}
