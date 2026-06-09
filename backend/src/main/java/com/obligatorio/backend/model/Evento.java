package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Evento")
public class Evento {

    @EmbeddedId
    private EventoId id;

    public Evento() {}

    public EventoId getId() { return id; }
    public void setId(EventoId id) { this.id = id; }
}
