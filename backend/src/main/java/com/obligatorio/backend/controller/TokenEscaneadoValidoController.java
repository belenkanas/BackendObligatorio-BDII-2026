package com.obligatorio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.TokenEscaneadoValido;
import com.obligatorio.backend.service.TokenEscaneadoValidoService;

@RestController
@RequestMapping("/tokens-escaneados")
@CrossOrigin(origins = "*")
public class TokenEscaneadoValidoController {
    
    @Autowired
    private TokenEscaneadoValidoService tokenEscaneadoService;

    @GetMapping
    public List<TokenEscaneadoValido> obtenerTodos() { 
        return tokenEscaneadoService.obtenerTodos(); 
    }

    @PostMapping
    public TokenEscaneadoValido crear(@RequestBody TokenEscaneadoValido token) { 
        return tokenEscaneadoService.crear(token); 
    }
}