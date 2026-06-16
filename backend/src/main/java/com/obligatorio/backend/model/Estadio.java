package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "estadio")
public class Estadio {

    @EmbeddedId
    private EstadioId id;

    public EstadioId getId() { return id; }

    public void setId(EstadioId id) { 
        this.id = id; 
    }
}