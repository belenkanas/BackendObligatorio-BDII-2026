package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class SectorEventoId implements Serializable {

    @Column(name = "nombre_sector")
    private String nombreSector;

    @Column(name = "estadioNombre")
    private String estadioNombre;

    @Column(name = "estadioDireccionPais")
    private String estadioDireccionPais;

    @Column(name = "estadioDireccionCiudad")
    private String estadioDireccionCiudad;

    @Column(name = "fecha_hora_partido")
    private LocalDateTime fechaHoraPartido;

    public SectorEventoId() {
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

    public LocalDateTime getFechaHoraPartido() {
        return fechaHoraPartido;
    }

    public void setFechaHoraPartido(LocalDateTime fechaHoraPartido) {
        this.fechaHoraPartido = fechaHoraPartido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectorEventoId)) return false;
        SectorEventoId that = (SectorEventoId) o;
        return Objects.equals(nombreSector, that.nombreSector) &&
                Objects.equals(estadioNombre, that.estadioNombre) &&
                Objects.equals(estadioDireccionPais, that.estadioDireccionPais) &&
                Objects.equals(estadioDireccionCiudad, that.estadioDireccionCiudad) &&
                Objects.equals(fechaHoraPartido, that.fechaHoraPartido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nombreSector,
                estadioNombre,
                estadioDireccionPais,
                estadioDireccionCiudad,
                fechaHoraPartido
        );
    }
}