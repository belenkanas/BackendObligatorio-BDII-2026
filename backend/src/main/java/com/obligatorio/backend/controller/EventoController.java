package com.obligatorio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Evento crear(@RequestBody Evento evento) { return eventoService.crear(evento); }
}