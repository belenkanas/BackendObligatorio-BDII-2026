package com.obligatorio.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EventoId implements Serializable {

    @Column(name = "estadioNombre", length = 100)
    private String estadioNombre;

    @Column(name = "estadioDireccionPais", length = 50)
    private String estadioDireccionPais;

    @Column(name = "estadioDireccionCiudad", length = 50)
    private String estadioDireccionCiudad;

    @Column(name = "fecha_hora_partido")
    private LocalDateTime fechaHoraPartido;

    @Column(name = "nombrePais_equipoLocal", length = 50)
    private String nombrePaisEquipoLocal;

    @Column(name = "nombrePais_equipoVisitante", length = 50)
    private String nombrePaisEquipoVisitante;

    public EventoId() {}

    public String getEstadioNombre() { return estadioNombre; }
    public void setEstadioNombre(String nom) { this.estadioNombre = nom; }

    public String getEstadioDireccionPais() { return estadioDireccionPais; }
    public void setEstadioDireccionPais(String dirP) { this.estadioDireccionPais = dirP; }
    
    public String getEstadioDireccionCiudad() { return estadioDireccionCiudad; }
    public void setEstadioDireccionCiudad(String dirC) { this.estadioDireccionCiudad = dirC; }

    public LocalDateTime getFechaHoraPartido() { return fechaHoraPartido; }
    public void setFechaHoraPartido(LocalDateTime fh) { this.fechaHoraPartido = fh; }

    public String getNombrePaisEquipoLocal() { return nombrePaisEquipoLocal; }
    public void setNombrePaisEquipoLocal(String local) { this.nombrePaisEquipoLocal = local; }

    public String getNombrePaisEquipoVisitante() { return nombrePaisEquipoVisitante; }
    public void setNombrePaisEquipoVisitante(String visitante) { this.nombrePaisEquipoVisitante = visitante; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventoId)) return false;
        EventoId that = (EventoId) o;
        return Objects.equals(estadioNombre, that.estadioNombre) &&
               Objects.equals(estadioDireccionPais, that.estadioDireccionPais) &&
               Objects.equals(estadioDireccionCiudad, that.estadioDireccionCiudad) &&
               Objects.equals(fechaHoraPartido, that.fechaHoraPartido) &&
               Objects.equals(nombrePaisEquipoLocal, that.nombrePaisEquipoLocal) &&
               Objects.equals(nombrePaisEquipoVisitante, that.nombrePaisEquipoVisitante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estadioNombre, estadioDireccionPais, estadioDireccionCiudad,
                fechaHoraPartido, nombrePaisEquipoLocal, nombrePaisEquipoVisitante);
    }
}