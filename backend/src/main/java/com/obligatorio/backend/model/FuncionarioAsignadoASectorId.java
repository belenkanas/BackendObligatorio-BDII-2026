package com.obligatorio.backend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FuncionarioAsignadoASectorId implements Serializable {

    @Column(name = "nroLegajo", length = 100)
    private String nroLegajo;

    @Column(name = "nombre_sector", length = 100)
    private String nombreSector;

    @Column(name = "estadioNombre", length = 100)
    private String estadioNombre;

    @Column(name = "estadioDireccionPais", length = 50)
    private String estadioDireccionPais;

    @Column(name = "estadioDireccionCiudad", length = 50)
    private String estadioDireccionCiudad;

    public FuncionarioAsignadoASectorId() {
    }

    public String getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(String nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
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
        if (!(o instanceof FuncionarioAsignadoASectorId)) return false;
        FuncionarioAsignadoASectorId that = (FuncionarioAsignadoASectorId) o;
        return Objects.equals(nroLegajo, that.nroLegajo)
                && Objects.equals(nombreSector, that.nombreSector)
                && Objects.equals(estadioNombre, that.estadioNombre)
                && Objects.equals(estadioDireccionPais, that.estadioDireccionPais)
                && Objects.equals(estadioDireccionCiudad, that.estadioDireccionCiudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nroLegajo,
                nombreSector,
                estadioNombre,
                estadioDireccionPais,
                estadioDireccionCiudad
        );
    }
}