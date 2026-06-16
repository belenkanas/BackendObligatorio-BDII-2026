package com.obligatorio.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class AdministradorGestionaEventoId implements Serializable {

    @Column(name = "id_administrador")
    private Integer id_administrador;

    @Column(name = "estadio_nombre", length = 100)
    private String estadio_nombre;

    @Column(name = "estadio_direccion_pais", length = 50)
    private String estadio_direccion_pais;

    @Column(name = "estadio_direccion_ciudad", length = 50)
    private String estadio_direccion_ciudad;

    @Column(name = "fecha_hora_partido")
    private LocalDateTime fecha_hora_partido;

    @Column(name = "nombre_pais_equipo_local", length = 50)
    private String nombre_pais_equipo_local;

    @Column(name = "nombre_pais_equipo_visitante", length = 50)
    private String nombre_pais_equipo_visitante;

    public AdministradorGestionaEventoId() {}

    public Integer getIdAdministrador() { return id_administrador; }
    public void setIdAdministrador(Integer id_administrador) { 
        this.id_administrador = id_administrador; 
    }

    public String getEstadioNombre() { return estadio_nombre; }
    public void setEstadioNombre(String estadio_nombre) { 
        this.estadio_nombre = estadio_nombre; 
    }

    public String getEstadioDireccionPais() { return estadio_direccion_pais; }
    public void setEstadioDireccionPais(String estadio_direccion_pais) { 
        this.estadio_direccion_pais = estadio_direccion_pais; 
    }

    public String getEstadioDireccionCiudad() { return estadio_direccion_ciudad; }
    public void setEstadioDireccionCiudad(String estadio_direccion_ciudad) { 
        this.estadio_direccion_ciudad = estadio_direccion_ciudad; 
    }

    public LocalDateTime getFechaHoraPartido() { return fecha_hora_partido; }
    public void setFechaHoraPartido(LocalDateTime fecha_hora_partido) { 
        this.fecha_hora_partido = fecha_hora_partido; }

    public String getNombrePaisEquipoLocal() { return nombre_pais_equipo_local; }
    public void setNombrePaisEquipoLocal(String nombre_pais_equipo_local) { this.nombre_pais_equipo_local = nombre_pais_equipo_local; }

    public String getNombrePaisEquipoVisitante() { return nombre_pais_equipo_visitante; }
    public void setNombrePaisEquipoVisitante(String nombre_pais_equipo_visitante) { 
        this.nombre_pais_equipo_visitante = nombre_pais_equipo_visitante; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdministradorGestionaEventoId)) return false;
        AdministradorGestionaEventoId that = (AdministradorGestionaEventoId) o;
        return Objects.equals(id_administrador, that.id_administrador) &&
               Objects.equals(estadio_nombre, that.estadio_nombre) &&
               Objects.equals(estadio_direccion_pais, that.estadio_direccion_pais) &&
               Objects.equals(estadio_direccion_ciudad, that.estadio_direccion_ciudad) &&
               Objects.equals(fecha_hora_partido, that.fecha_hora_partido) &&
               Objects.equals(nombre_pais_equipo_local, that.nombre_pais_equipo_local) &&
               Objects.equals(nombre_pais_equipo_visitante, that.nombre_pais_equipo_visitante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_administrador, estadio_nombre, estadio_direccion_pais,
                estadio_direccion_ciudad, fecha_hora_partido, nombre_pais_equipo_local,
                nombre_pais_equipo_visitante);
    }
}