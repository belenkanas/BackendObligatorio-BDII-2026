package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrador_gestiona_evento")
public class AdministradorGestionaEvento {

    @EmbeddedId
    private AdministradorGestionaEventoId id;

    public AdministradorGestionaEvento() {}

    public AdministradorGestionaEventoId getId() { return id; }
    public void setId(AdministradorGestionaEventoId id) { this.id = id; }
}