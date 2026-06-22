package com.obligatorio.backend.dto;

import com.obligatorio.backend.model.EventoId;

public class CrearEventoRequest {

    private EventoId id;
    private Integer idAdministrador;

    public EventoId getId() { return id; }
    public void setId(EventoId id) { this.id = id; }

    public Integer getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(Integer idAdministrador) { this.idAdministrador = idAdministrador; }
}