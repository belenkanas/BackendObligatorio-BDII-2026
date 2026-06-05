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

import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.service.PerfilService;

@RestController
@RequestMapping("/perfiles")
@CrossOrigin(origins = "*")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Perfil> obtenerTodos() {
        return perfilService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Perfil> obtenerPorId(@PathVariable Integer id) {
        return perfilService.obtenerPorId(id);
    }

    @GetMapping("/usuario/{mail}")
    public List<Perfil> obtenerPorUsuario(@PathVariable String mail) {
        return perfilService.obtenerPorUsuario(mail);
    }

    @PostMapping
    public Perfil crear(@RequestBody Perfil perfil) {
        return perfilService.crear(perfil);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        perfilService.eliminar(id);
    }
}
