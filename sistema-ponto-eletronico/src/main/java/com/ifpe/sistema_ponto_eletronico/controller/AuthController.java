package com.ifpe.sistema_ponto_eletronico.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.config.security.TokenService;
import com.ifpe.sistema_ponto_eletronico.dto.EmpresaRequestDTO;
import com.ifpe.sistema_ponto_eletronico.dto.FuncionarioLoginRequestDTO;
import com.ifpe.sistema_ponto_eletronico.dto.FuncionarioLoginResponseDTO;
import com.ifpe.sistema_ponto_eletronico.model.Empresa;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.enumerable.UserType;
import com.ifpe.sistema_ponto_eletronico.repository.EmpresaRepository;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import com.ifpe.sistema_ponto_eletronico.service.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmpresaRepository empresaRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody FuncionarioLoginRequestDTO body) {
        Funcionario funcionario = this.funcionarioRepository.findByMatricula(body.matricula())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Usuário não encontrado! Para matricula: " + body.matricula()));
        if (passwordEncoder.matches(body.senha(), funcionario.getSenha())) {
            String token = this.tokenService.generateToken(funcionario);
            return ResponseEntity.ok(new FuncionarioLoginResponseDTO(funcionario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody EmpresaRequestDTO body) {
        Optional<Funcionario> func = funcionarioRepository.findByMatricula(body.matriculaAdmin());

        if (func.isEmpty()) {
            Empresa empresa = new Empresa();
            empresa.setNomeEmpresa(body.nomeEmpresa());
            empresa.setCnpj(body.cnpj());
            empresa.setTelefone(body.telefone());

            empresa = empresaRepository.save(empresa);

            Funcionario admin = new Funcionario();
            admin.setNome(body.nomeAdmin());
            admin.setMatricula(body.matriculaAdmin());
            admin.setSenha(passwordEncoder.encode(body.senhaAdmin()));
            admin.setCpf(body.cpfAdmin());
            admin.setEmail(body.emailAdmin());
            admin.setRole(UserType.ADMIN);
            admin.setEmpresa(empresa);

            funcionarioRepository.save(admin);

            String token = this.tokenService.generateToken(admin);
            return ResponseEntity.ok(new FuncionarioLoginResponseDTO(admin.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
