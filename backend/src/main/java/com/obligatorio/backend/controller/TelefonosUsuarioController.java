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

import com.obligatorio.backend.model.TelefonosUsuario;
import com.obligatorio.backend.model.TelefonosUsuarioId;
import com.obligatorio.backend.service.TelefonosUsuarioService;

@RestController
@RequestMapping("/telefonos-usuario")
@CrossOrigin(origins = "*")
public class TelefonosUsuarioController {

    @Autowired
    private TelefonosUsuarioService telefonosUsuarioService;

    @GetMapping
    public List<TelefonosUsuario> obtenerTodos() {
        return telefonosUsuarioService.obtenerTodos();
    }

    @GetMapping("/{mailUsuario}/{telefono}")
    public Optional<TelefonosUsuario> obtenerPorId(
            @PathVariable String mailUsuario,
            @PathVariable String telefono) {
        TelefonosUsuarioId id = new TelefonosUsuarioId();
        id.setMailUsuario(mailUsuario);
        id.setTelefono(telefono);
        return telefonosUsuarioService.obtenerPorId(id);
    }

    @PostMapping
    public TelefonosUsuario crear(@RequestBody TelefonosUsuario telefonosUsuario) {
        return telefonosUsuarioService.crear(telefonosUsuario);
    }

    @DeleteMapping("/{mailUsuario}/{telefono}")
    public void eliminar(
            @PathVariable String mailUsuario,
            @PathVariable String telefono) {
        TelefonosUsuarioId id = new TelefonosUsuarioId();
        id.setMailUsuario(mailUsuario);
        id.setTelefono(telefono);
        telefonosUsuarioService.eliminar(id);
    }
}