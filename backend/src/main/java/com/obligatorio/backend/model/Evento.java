package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {

    @EmbeddedId
    private EventoId id;

    @Column(name = "estado", nullable = false)
    private String estado = "activo";

    public Evento() {}

    public EventoId getId() { return id; }
    public void setId(EventoId id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
