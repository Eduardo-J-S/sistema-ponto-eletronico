package com.ifpe.sistema_ponto_eletronico.config.security;

import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionario não encontrado com a matrícula: " + matricula));

        // Como a classe Funcionario já implementa UserDetails, pode ser retornada diretamente
        return funcionario;
    }
}
