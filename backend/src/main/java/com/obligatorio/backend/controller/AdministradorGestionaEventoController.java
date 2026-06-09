package com.obligatorio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.AdministradorGestionaEvento;
import com.obligatorio.backend.service.AdministradorGestionaEventoService;

@RestController
@RequestMapping("/admin-eventos")
@CrossOrigin(origins = "*")
public class AdministradorGestionaEventoController {
    
    @Autowired
    private AdministradorGestionaEventoService adminGestionaEventoService;

    @GetMapping
    public List<AdministradorGestionaEvento> obtenerTodos() { 
        return adminGestionaEventoService.obtenerTodos(); 
    }

    @PostMapping
    public AdministradorGestionaEvento crear(@RequestBody AdministradorGestionaEvento admin) { 
        return adminGestionaEventoService.crear(admin); 
    }
}