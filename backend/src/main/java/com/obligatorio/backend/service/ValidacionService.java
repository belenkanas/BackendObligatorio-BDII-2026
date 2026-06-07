package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Validacion;
import com.obligatorio.backend.model.ValidacionId;
import com.obligatorio.backend.repository.ValidacionRepository;

@Service
public class ValidacionService {

    @Autowired
    private ValidacionRepository validacionRepository;

    public List<Validacion> obtenerTodos() {
        return validacionRepository.findAll();
    }

    public Optional<Validacion> obtenerPorId(ValidacionId id) {
        return validacionRepository.findById(id);
    }

    public Validacion crear(Validacion validacion) {
        return validacionRepository.save(validacion);
    }

    public void eliminar(ValidacionId id) {
        validacionRepository.deleteById(id);
    }
}