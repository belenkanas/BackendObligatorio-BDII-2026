package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "validacion")
public class Validacion {

    @EmbeddedId
    private ValidacionId id;

    public Validacion() {
    }

    public ValidacionId getId() {
        return id;
    }

    public void setId(ValidacionId id) {
        this.id = id;
    }
}