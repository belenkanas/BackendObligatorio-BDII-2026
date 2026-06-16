package com.obligatorio.backend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FuncionarioAsignadoASectorId implements Serializable {

    @Column(name = "nro_legajo", length = 100)
    private String nroLegajo;

    @Column(name = "nombre_sector", length = 100)
    private String nombreSector;

    @Column(name = "estadio_nombre", length = 100)
    private String estadioNombre;

    @Column(name = "estadio_direccion_pais", length = 50)
    private String estadioDireccionPais;

    @Column(name = "estadio_direccion_ciudad", length = 50)
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