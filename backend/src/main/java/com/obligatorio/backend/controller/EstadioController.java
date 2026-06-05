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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.Estadio;
import com.obligatorio.backend.model.EstadioId;
import com.obligatorio.backend.service.EstadioService;

@RestController
@RequestMapping("/estadios")
@CrossOrigin(origins = "*")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;

    @GetMapping
    public List<Estadio> obtenerTodos() {
        return estadioService.obtenerTodos();
    }

    @GetMapping("/buscar")
    public Optional<Estadio> obtenerPorId(
            @RequestParam String nombre,
            @RequestParam String pais,
            @RequestParam String ciudad) {
        EstadioId id = new EstadioId();
        id.setNombre(nombre);
        id.setDireccion_pais(pais);
        id.setDireccion_ciudad(ciudad);
        return estadioService.obtenerPorId(id);
    }

    @GetMapping("/pais/{pais}")
    public List<Estadio> obtenerPorPais(@PathVariable String pais) {
        return estadioService.obtenerPorPais(pais);
    }

    @PostMapping
    public Estadio crear(@RequestBody Estadio estadio) {
        return estadioService.crear(estadio);
    }

    @DeleteMapping("/eliminar")
    public void eliminar(
            @RequestParam String nombre,
            @RequestParam String pais,
            @RequestParam String ciudad) {
        EstadioId id = new EstadioId();
        id.setNombre(nombre);
        id.setDireccion_pais(pais);
        id.setDireccion_ciudad(ciudad);
        estadioService.eliminar(id);
    }
}
