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

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.service.EntradaService;

@RestController
@RequestMapping("/entradas")
@CrossOrigin(origins = "*")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public List<Entrada> obtenerTodos() {
        return entradaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Entrada> obtenerPorId(@PathVariable Integer id) {
        return entradaService.obtenerPorId(id);
    }

    @PostMapping
    public Entrada crear(@RequestBody Entrada entrada) {
        return entradaService.crear(entrada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        entradaService.eliminar(id);
    }
}