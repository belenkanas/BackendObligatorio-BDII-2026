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

import com.obligatorio.backend.model.Partido;
import com.obligatorio.backend.service.PartidoService;

@RestController
@RequestMapping("/partidos")
@CrossOrigin(origins = "*")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public List<Partido> obtenerTodos() {
        return partidoService.obtenerTodos();
    }

    @GetMapping("/{fechaHora}")
    public Optional<Partido> obtenerPorFechaHora(@PathVariable String fechaHora) {
        return partidoService.obtenerPorFechaHora(LocalDateTime.parse(fechaHora));
    }

    @PostMapping
    public Partido crear(@RequestBody Partido partido) {
        return partidoService.crear(partido);
    }

    @DeleteMapping("/{fechaHora}")
    public void eliminar(@PathVariable String fechaHora) {
        partidoService.eliminar(LocalDateTime.parse(fechaHora));
    }
}
