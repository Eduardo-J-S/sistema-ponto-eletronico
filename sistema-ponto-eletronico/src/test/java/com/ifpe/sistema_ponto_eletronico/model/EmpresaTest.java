package com.ifpe.sistema_ponto_eletronico.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmpresaTest {
    private Empresa empresa;

    @BeforeEach
    void setUp(){
        empresa = new Empresa();
    }

    @Test
    void testGettersAndSetters(){
        empresa.setCnpj("36.425.736/0006-82");
        assertEquals("36.425.736/0006-82",empresa.getCnpj());
        
        empresa.setNomeEmpresa("fullstacks perfect");
        assertEquals("fullstacks perfect",empresa.getNomeEmpresa());
        
        empresa.setTelefone("(70)98403-3960");
        assertEquals("(70)98403-3960",empresa.getTelefone());

        Set<Funcionario> funcionarios = new HashSet<>();
        empresa.setFuncionarios(funcionarios);
        assertEquals(funcionarios, empresa.getFuncionarios());

        Set<Admin> administradores = new HashSet<>();
        empresa.setAdministradores(administradores);
        assertEquals(administradores, empresa.getAdministradores());
    
    }
    @Test
    void testUniqueCnpj(){
        empresa.setCnpj("36.425.736/0006-82");
        assertNotNull(empresa.getCnpj());
        assertEquals("36.425.736/0006-82", empresa.getCnpj());

    }
    
}
