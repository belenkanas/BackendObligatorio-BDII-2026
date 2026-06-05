package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Administrador;
import com.obligatorio.backend.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> obtenerPorId(Integer id) {
        return administradorRepository.findById(id);
    }

    public Administrador crear(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void eliminar(Integer id) {
        administradorRepository.deleteById(id);
    }
}
