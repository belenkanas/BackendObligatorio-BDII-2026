package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Token;
import com.obligatorio.backend.repository.TokenRepository;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public List<Token> obtenerTodos() { 
        return tokenRepository.findAll(); 
    }

    public Optional<Token> obtenerPorQr(String qr) { 
        return tokenRepository.findById(qr); 
    }

    public Token crear(Token token) { 
        return tokenRepository.save(token); 
    }

    public void eliminar(String qr) { 
        tokenRepository.deleteById(qr); 
    }
}