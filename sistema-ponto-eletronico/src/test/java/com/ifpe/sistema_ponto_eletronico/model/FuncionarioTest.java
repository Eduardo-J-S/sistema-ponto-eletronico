// package com.ifpe.sistema_ponto_eletronico.model;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import jakarta.validation.ConstraintViolation;
// import jakarta.validation.Validation;
// import jakarta.validation.Validator;
// import jakarta.validation.ValidatorFactory;

// import java.util.Set;

// import static org.junit.jupiter.api.Assertions.*;


// public class FuncionarioTest {
//     private Funcionario funcionario;
//     private Validator validator;

//     @BeforeEach
//     void setUp(){
//         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         validator = factory.getValidator();
//         funcionario = new Funcionario();
//     }
    
//     @Test
//     void testnomeVadidation(){
//         funcionario.setNome(null);
//         Set<ConstraintViolation<Funcionario>> violations = validator.validate(funcionario);
//         assertEquals("Nome é obrigatorio", violations.iterator().next().getMessage());

//         funcionario.setNome("cd");
//         violations = validator.validate(funcionario);
//         assertEquals(1, violations.size());
//         assertEquals("Nome deve ter entre 2 e 100 caracteres", violations.iterator().next().getMessage());
        
//         funcionario.setNome("pablo diego jose francisco de paula juan nepomuceno crispim crispiniano de la santissima trinidade juiz blasco e lopez picasso da silva ribeiro dos santos moura do nascimento ");
//         violations = validator.validate(funcionario);
//         assertEquals(1, violations.size());
//         assertEquals("Nome deve ter entre 2 e 100 caracteres", violations.iterator().next().getMessage());

//         funcionario.setEmail(null);
//         Set<ConstraintViolation<Funcionario>> violations1 = validator.validate(funcionario);
//         assertEquals(1, violations1.size());
//         assertEquals("A matricula não pode ser nula", violations1.iterator().next().getMessage());
        
//         funcionario.setCpf(null);
//         Set<ConstraintViolation<Funcionario>> violations2 = validator.validate(funcionario);
//         assertEquals(1, violations2.size());
//         assertEquals("CPF é obrigatório", violations2.iterator().next().getMessage());

//         funcionario.setSenha("84809");
//         violations = validator.validate(funcionario);
//         assertEquals(1, violations.size());
//         assertEquals("Senha deve ter pelo menos 8 caracteres", violations.iterator().next().getMessage());
        
//         funcionario.setSenha("yfpanmsnçlktçj");
//         violations = validator.validate(funcionario);
//         assertTrue(violations.isEmpty());
    
//     }
    
// }
