package com.obligatorio.backend.controller;

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

import com.obligatorio.backend.model.Sector;
import com.obligatorio.backend.model.SectorId;
import com.obligatorio.backend.service.SectorService;

@RestController
@RequestMapping("/sectores")
@CrossOrigin(origins = "*")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public List<Sector> obtenerTodos() {
        return sectorService.obtenerTodos();
    }

    @GetMapping("/{nombre}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}")
    public Optional<Sector> obtenerPorId(
            @PathVariable String nombre,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad) {
        SectorId id = new SectorId();
        id.setNombre(nombre);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        return sectorService.obtenerPorId(id);
    }

    @PostMapping
    public Sector crear(@RequestBody Sector sector) {
        return sectorService.crear(sector);
    }

    @DeleteMapping("/{nombre}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}")
    public void eliminar(
            @PathVariable String nombre,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad) {
        SectorId id = new SectorId();
        id.setNombre(nombre);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        sectorService.eliminar(id);
    }
}