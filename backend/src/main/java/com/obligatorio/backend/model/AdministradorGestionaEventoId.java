package com.obligatorio.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class AdministradorGestionaEventoId implements Serializable {

    @Column(name = "id_administrador")
    private Integer idAdministrador;

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

    public AdministradorGestionaEventoId() {}

    public Integer getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(Integer idAdministrador) { this.idAdministrador = idAdministrador; }

    public String getEstadioNombre() { return estadioNombre; }
    public void setEstadioNombre(String estadioNombre) { this.estadioNombre = estadioNombre; }

    public String getEstadioDireccionPais() { return estadioDireccionPais; }
    public void setEstadioDireccionPais(String estadioDireccionPais) { this.estadioDireccionPais = estadioDireccionPais; }

    public String getEstadioDireccionCiudad() { return estadioDireccionCiudad; }
    public void setEstadioDireccionCiudad(String estadioDireccionCiudad) { this.estadioDireccionCiudad = estadioDireccionCiudad; }

    public LocalDateTime getFechaHoraPartido() { return fechaHoraPartido; }
    public void setFechaHoraPartido(LocalDateTime fechaHoraPartido) { this.fechaHoraPartido = fechaHoraPartido; }

    public String getNombrePaisEquipoLocal() { return nombrePaisEquipoLocal; }
    public void setNombrePaisEquipoLocal(String nombrePaisEquipoLocal) { this.nombrePaisEquipoLocal = nombrePaisEquipoLocal; }

    public String getNombrePaisEquipoVisitante() { return nombrePaisEquipoVisitante; }
    public void setNombrePaisEquipoVisitante(String nombrePaisEquipoVisitante) { this.nombrePaisEquipoVisitante = nombrePaisEquipoVisitante; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdministradorGestionaEventoId)) return false;
        AdministradorGestionaEventoId that = (AdministradorGestionaEventoId) o;
        return Objects.equals(idAdministrador, that.idAdministrador) &&
               Objects.equals(estadioNombre, that.estadioNombre) &&
               Objects.equals(estadioDireccionPais, that.estadioDireccionPais) &&
               Objects.equals(estadioDireccionCiudad, that.estadioDireccionCiudad) &&
               Objects.equals(fechaHoraPartido, that.fechaHoraPartido) &&
               Objects.equals(nombrePaisEquipoLocal, that.nombrePaisEquipoLocal) &&
               Objects.equals(nombrePaisEquipoVisitante, that.nombrePaisEquipoVisitante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdministrador, estadioNombre, estadioDireccionPais,
                estadioDireccionCiudad, fechaHoraPartido, nombrePaisEquipoLocal,
                nombrePaisEquipoVisitante);
    }
}