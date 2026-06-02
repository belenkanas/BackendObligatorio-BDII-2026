package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "SectorEvento")
public class SectorEvento {

    @EmbeddedId
    private SectorEventoId id;

    @Column(name = "costo", nullable = false)
    private BigDecimal costo;

    public SectorEvento() {
    }

    public SectorEventoId getId() {
        return id;
    }

    public void setId(SectorEventoId id) {
        this.id = id;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}