package com.obligatorio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.EntradaTieneToken;
import com.obligatorio.backend.service.EntradaTieneTokenService;

@RestController
@RequestMapping("/entrada-tokens")
@CrossOrigin(origins = "*")
public class EntradaTieneTokenController {
    
    @Autowired
    private EntradaTieneTokenService entradaTieneTokenService;

    @GetMapping
    public List<EntradaTieneToken> obtenerTodos() { 
        return entradaTieneTokenService.obtenerTodos(); 
    }

    @PostMapping
    public EntradaTieneToken crear(@RequestBody EntradaTieneToken entrada) { 
        return entradaTieneTokenService.crear(entrada); 
    }
}