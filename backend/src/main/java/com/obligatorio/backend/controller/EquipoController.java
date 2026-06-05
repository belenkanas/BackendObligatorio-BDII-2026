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

import com.obligatorio.backend.model.Equipo;
import com.obligatorio.backend.service.EquipoService;

@RestController
@RequestMapping("/equipos")
@CrossOrigin(origins = "*")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> obtenerTodos() {
        return equipoService.obtenerTodos();
    }

    @GetMapping("/{nombrePais}")
    public Optional<Equipo> obtenerPorNombrePais(@PathVariable String nombrePais) {
        return equipoService.obtenerPorNombrePais(nombrePais);
    }

    @PostMapping
    public Equipo crear(@RequestBody Equipo equipo) {
        return equipoService.crear(equipo);
    }

    @DeleteMapping("/{nombrePais}")
    public void eliminar(@PathVariable String nombrePais) {
        equipoService.eliminar(nombrePais);
    }
}
