package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SectorId implements Serializable {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estadioNombre")
    private String estadioNombre;

    @Column(name = "estadioDireccionPais")
    private String estadioDireccionPais;

    @Column(name = "estadioDireccionCiudad")
    private String estadioDireccionCiudad;

    public SectorId() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadioNombre() {
        return estadioNombre;
    }

    public void setEstadioNombre(String estadioNombre) {
        this.estadioNombre = estadioNombre;
    }

    public String getEstadioDireccionPais() {
        return estadioDireccionPais;
    }

    public void setEstadioDireccionPais(String estadioDireccionPais) {
        this.estadioDireccionPais = estadioDireccionPais;
    }

    public String getEstadioDireccionCiudad() {
        return estadioDireccionCiudad;
    }

    public void setEstadioDireccionCiudad(String estadioDireccionCiudad) {
        this.estadioDireccionCiudad = estadioDireccionCiudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectorId)) return false;
        SectorId sectorId = (SectorId) o;
        return Objects.equals(nombre, sectorId.nombre) &&
               Objects.equals(estadioNombre, sectorId.estadioNombre) &&
               Objects.equals(estadioDireccionPais, sectorId.estadioDireccionPais) &&
               Objects.equals(estadioDireccionCiudad, sectorId.estadioDireccionCiudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, estadioNombre, estadioDireccionPais, estadioDireccionCiudad);
    }
}