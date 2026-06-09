package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;

public interface EventoRepository extends JpaRepository<Evento, EventoId> {
}