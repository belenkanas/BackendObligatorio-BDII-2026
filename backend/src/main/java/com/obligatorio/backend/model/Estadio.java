package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Estadio")
public class Estadio {

    @EmbeddedId
    private EstadioId id;

    // Getters y Setters
    public EstadioId getId() { return id; }
    public void setId(EstadioId id) { this.id = id; }
}