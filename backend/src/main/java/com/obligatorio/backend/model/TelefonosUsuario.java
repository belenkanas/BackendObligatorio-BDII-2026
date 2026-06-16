package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefonos_usuario")
public class TelefonosUsuario {

    @EmbeddedId
    private TelefonosUsuarioId id;

    public TelefonosUsuario() {
    }

    public TelefonosUsuarioId getId() {
        return id;
    }

    public void setId(TelefonosUsuarioId id) {
        this.id = id;
    }
}