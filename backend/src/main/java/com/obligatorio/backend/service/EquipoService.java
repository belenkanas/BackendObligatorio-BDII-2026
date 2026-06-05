package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Equipo;
import com.obligatorio.backend.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> obtenerPorNombrePais(String nombrePais) {
        return equipoRepository.findById(nombrePais);
    }

    public Equipo crear(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void eliminar(String nombrePais) {
        equipoRepository.deleteById(nombrePais);
    }
}
