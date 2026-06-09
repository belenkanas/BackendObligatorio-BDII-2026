package com.obligatorio.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.AdministradorGestionaEvento;
import com.obligatorio.backend.repository.AdministradorGestionaEventoRepository;

@Service
public class AdministradorGestionaEventoService {
    
    @Autowired
    private AdministradorGestionaEventoRepository administradorGestionaEventoRepository;

    public List<AdministradorGestionaEvento> obtenerTodos() { 
        return administradorGestionaEventoRepository.findAll(); 
    }

    public AdministradorGestionaEvento crear(AdministradorGestionaEvento admin) { 
        return administradorGestionaEventoRepository.save(admin); 
    }
}