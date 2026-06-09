package com.obligatorio.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entrada_tiene_Token")
public class EntradaTieneToken {

    @EmbeddedId
    private EntradaTieneTokenId id;

    @Column(name = "fecha_caducidad")
    private LocalDateTime fechaCaducidad;

    public EntradaTieneToken() {}

    public EntradaTieneTokenId getId() { 
        return id; 
    }
    
    public void setId(EntradaTieneTokenId id) { 
        this.id = id; 
    }
    
    public LocalDateTime getFechaCaducidad() { 
        return fechaCaducidad; 
    }

    public void setFechaCaducidad(LocalDateTime fechaCaducidad) { 
        this.fechaCaducidad = fechaCaducidad; 
    }
}
