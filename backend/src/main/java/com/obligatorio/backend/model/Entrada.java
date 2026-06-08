package com.obligatorio.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entrada")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "estado")
    private String estado;

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

    @Column(name = "nombrePais_equipoLocal")
    private String nombrePaisEquipoLocal;

    @Column(name = "nombrePais_equipoVisitante")
    private String nombrePaisEquipoVisitante;

    @Column(name = "id_general_propietario")
    private Integer idGeneralPropietario;

    @Column(name = "id_venta")
    private Integer idVenta;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getNombrePaisEquipoLocal() {
        return nombrePaisEquipoLocal;
    }

    public void setNombrePaisEquipoLocal(String nombrePaisEquipoLocal) {
        this.nombrePaisEquipoLocal = nombrePaisEquipoLocal;
    }

    public String getNombrePaisEquipoVisitante() {
        return nombrePaisEquipoVisitante;
    }

    public void setNombrePaisEquipoVisitante(String nombrePaisEquipoVisitante) {
        this.nombrePaisEquipoVisitante = nombrePaisEquipoVisitante;
    }

    public Integer getIdGeneralPropietario() {
        return idGeneralPropietario;
    }

    public void setIdGeneralPropietario(Integer idGeneralPropietario) {
        this.idGeneralPropietario = idGeneralPropietario;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }
}