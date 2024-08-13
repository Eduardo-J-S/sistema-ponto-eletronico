package com.ifpe.sistema_ponto_eletronico.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {
    private Admin admin;
    private Empresa empresa;

    @BeforeEach
    void setUp(){
        empresa = new Empresa();
        empresa.setNomeEmpresa("Empresa fullstack ponto");

        admin = new Admin();
        admin.setIdAdmin(1L);
        admin.setNomeAdmin("eduardo");
        admin.setSenhaAdmin("52678");
        admin.setDataAlteracao(LocalDateTime.now());
        admin.setUsuarioAdmin("eduardo");
        admin.setMatricula("2022ads4537");
        admin.setEmpresa(empresa);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, admin.getIdAdmin());
        assertEquals("eduardo", admin.getNomeAdmin());
        assertEquals("52678", admin.getSenhaAdmin());
        assertNotNull(admin.getDataAlteracao());
        assertEquals("eduardo", admin.getUsuarioAdmin());
        assertEquals("2022ads4537", admin.getMatricula());
        assertEquals(empresa, admin.getEmpresa());
    }

    @Test
    void testEqualsAndHashCode() {
        Admin admin2 = new Admin();
        admin2.setIdAdmin(1L);

        assertEquals(admin, admin2);
        assertEquals(admin.hashCode(), admin2.hashCode());
    }
}
