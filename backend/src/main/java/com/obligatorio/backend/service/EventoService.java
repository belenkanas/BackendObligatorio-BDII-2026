package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;
import com.obligatorio.backend.repository.EventoRepository;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> obtenerTodos() { return eventoRepository.findAll(); }

    public Optional<Evento> obtenerPorId(EventoId id) { return eventoRepository.findById(id); }
    
    public Evento crear(Evento evento) { return eventoRepository.save(evento); }
    
    public void eliminar(EventoId id) { eventoRepository.deleteById(id); }
}