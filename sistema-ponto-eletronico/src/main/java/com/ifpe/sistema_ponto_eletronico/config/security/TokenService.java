package com.ifpe.sistema_ponto_eletronico.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(Funcionario funcionario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("sistema-ponto-eletronico")
                .withSubject(funcionario.getMatricula())
                .withExpiresAt(this.generateExpirationDate())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException creationException) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            
            return JWT.require(algorithm)
                .withIssuer("sistema-ponto-eletronico")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        LocalDateTime nowPlus2Hours = LocalDateTime.now().plusHours(1);
        Instant instant = nowPlus2Hours.toInstant(ZoneOffset.of("-03:00"));

        return instant;
    }
}
