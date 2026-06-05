package com.obligatorio.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sector")

public class Sector {

    @EmbeddedId
    private SectorId id;

    @Column(name = "capacidadMax", nullable = false)
    private Integer capacidadMax;

    public Sector() {
    }

    public SectorId getId() {
        return id;
    }

    public void setId(SectorId id) {
        this.id = id;
    }

    public Integer getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(Integer capacidadMax) {
        this.capacidadMax = capacidadMax;
    }
}
