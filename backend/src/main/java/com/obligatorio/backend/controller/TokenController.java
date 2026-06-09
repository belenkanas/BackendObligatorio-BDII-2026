package com.obligatorio.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.Token;
import com.obligatorio.backend.service.TokenService;

@RestController
@RequestMapping("/tokens")
@CrossOrigin(origins = "*")
public class TokenController {
    
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<Token> obtenerTodos() { 
        return tokenService.obtenerTodos(); 
    }

    @GetMapping("/{qr}")
    public Optional<Token> obtenerPorQr(@PathVariable String qr) { 
        return tokenService.obtenerPorQr(qr); 
    }

    @PostMapping
    public Token crear(@RequestBody Token token) { 
        return tokenService.crear(token); 
    }

    @DeleteMapping("/{qr}")
    public void eliminar(@PathVariable String qr) { 
        tokenService.eliminar(qr); 
    }
}
