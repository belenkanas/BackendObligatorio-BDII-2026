package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;
import com.obligatorio.backend.repository.SectorEventoRepository;

@Service
public class SectorEventoService {

    @Autowired
    private SectorEventoRepository sectorEventoRepository;

    public List<SectorEvento> obtenerTodos() {
        return sectorEventoRepository.findAll();
    }

    public Optional<SectorEvento> obtenerPorId(SectorEventoId id) {
        return sectorEventoRepository.findById(id);
    }

    public SectorEvento crear(SectorEvento sectorEvento) {
        return sectorEventoRepository.save(sectorEvento);
    }

    public void eliminar(SectorEventoId id) {
        sectorEventoRepository.deleteById(id);
    }
}