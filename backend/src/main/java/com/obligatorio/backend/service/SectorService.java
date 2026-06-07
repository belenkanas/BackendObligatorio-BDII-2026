package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Sector;
import com.obligatorio.backend.model.SectorId;
import com.obligatorio.backend.repository.SectorRepository;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> obtenerTodos() {
        return sectorRepository.findAll();
    }

    public Optional<Sector> obtenerPorId(SectorId id) {
        return sectorRepository.findById(id);
    }

    public Sector crear(Sector sector) {
        return sectorRepository.save(sector);
    }

    public void eliminar(SectorId id) {
        sectorRepository.deleteById(id);
    }
}