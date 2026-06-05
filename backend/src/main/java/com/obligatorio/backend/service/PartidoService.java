package com.obligatorio.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Partido;
import com.obligatorio.backend.repository.PartidoRepository;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    public List<Partido> obtenerTodos() {
        return partidoRepository.findAll();
    }

    public Optional<Partido> obtenerPorFechaHora(LocalDateTime fechaHora) {
        return partidoRepository.findById(fechaHora);
    }

    public Partido crear(Partido partido) {
        return partidoRepository.save(partido);
    }

    public void eliminar(LocalDateTime fechaHora) {
        partidoRepository.deleteById(fechaHora);
    }
}
