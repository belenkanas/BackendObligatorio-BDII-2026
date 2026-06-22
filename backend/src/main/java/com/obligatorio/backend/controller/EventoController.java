package com.obligatorio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.dto.CrearEventoRequest;
import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.service.EventoService;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> obtenerTodos() { return eventoService.obtenerTodos(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearEventoRequest datos) {
        if (datos.getIdAdministrador() == null) {
            return ResponseEntity.badRequest().body("El id del administrador es obligatorio");
        }
        Evento evento = new Evento();
        evento.setId(datos.getId());
        Evento creado = eventoService.crear(evento, datos.getIdAdministrador());
        return ResponseEntity.ok(creado);
    }
}