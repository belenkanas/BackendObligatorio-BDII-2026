package com.obligatorio.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;
import com.obligatorio.backend.service.SectorEventoService;

@RestController
@RequestMapping("/sector-eventos")
@CrossOrigin(origins = "*")
public class SectorEventoController {

    @Autowired
    private SectorEventoService sectorEventoService;

    @GetMapping
    public List<SectorEvento> obtenerTodos() {
        return sectorEventoService.obtenerTodos();
    }

    @GetMapping("/{nombreSector}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}/{fechaHoraPartido}")
    public Optional<SectorEvento> obtenerPorId(
            @PathVariable String nombreSector,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad,
            @PathVariable String fechaHoraPartido) {
        SectorEventoId id = new SectorEventoId();
        id.setNombreSector(nombreSector);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        id.setFechaHoraPartido(LocalDateTime.parse(fechaHoraPartido));
        return sectorEventoService.obtenerPorId(id);
    }

    @PostMapping
    public SectorEvento crear(@RequestBody SectorEvento sectorEvento) {
        return sectorEventoService.crear(sectorEvento);
    }

    @DeleteMapping("/{nombreSector}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}/{fechaHoraPartido}")
    public void eliminar(
            @PathVariable String nombreSector,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad,
            @PathVariable String fechaHoraPartido) {
        SectorEventoId id = new SectorEventoId();
        id.setNombreSector(nombreSector);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        id.setFechaHoraPartido(LocalDateTime.parse(fechaHoraPartido));
        sectorEventoService.eliminar(id);
    }
}