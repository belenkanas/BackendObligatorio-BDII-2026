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

import com.obligatorio.backend.model.Administrador;
import com.obligatorio.backend.service.AdministradorService;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> obtenerTodos() {
        return administradorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> obtenerPorId(@PathVariable Integer id) {
        return administradorService.obtenerPorId(id);
    }

    @PostMapping
    public Administrador crear(@RequestBody Administrador administrador) {
        return administradorService.crear(administrador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        administradorService.eliminar(id);
    }
}
