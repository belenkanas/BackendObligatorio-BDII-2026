package com.obligatorio.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.TokenEscaneadoValido;
import com.obligatorio.backend.repository.TokenEscaneadoValidoRepository;

@Service
public class TokenEscaneadoValidoService {
    
    @Autowired
    private TokenEscaneadoValidoRepository tokenEscaneadoRepository;

    public List<TokenEscaneadoValido> obtenerTodos() { 
        return tokenEscaneadoRepository.findAll(); 
    }

    public TokenEscaneadoValido crear(TokenEscaneadoValido token) { 
        return tokenEscaneadoRepository.save(token); 
    }
}