package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Estadio;
import com.obligatorio.backend.model.EstadioId;
import com.obligatorio.backend.repository.EstadioRepository;

@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    public List<Estadio> obtenerTodos() {
        return estadioRepository.findAll();
    }

    public Optional<Estadio> obtenerPorId(EstadioId id) {
        return estadioRepository.findById(id);
    }

    public Estadio crear(Estadio estadio) {
        return estadioRepository.save(estadio);
    }

    public void eliminar(EstadioId id) {
        estadioRepository.deleteById(id);
    }
}
