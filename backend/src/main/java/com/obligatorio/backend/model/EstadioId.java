package com.obligatorio.backend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class EstadioId implements Serializable {

    private String nombre;
    private String direccion_pais;
    private String direccion_ciudad;

    public EstadioId() {
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion_pais() { return direccion_pais; }
    public void setDireccion_pais(String direccion_pais) { this.direccion_pais = direccion_pais; }

    public String getDireccion_ciudad() { return direccion_ciudad; }
    public void setDireccion_ciudad(String direccion_ciudad) { this.direccion_ciudad = direccion_ciudad; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstadioId)) return false;
        EstadioId that = (EstadioId) o;
        return Objects.equals(nombre, that.nombre) &&
               Objects.equals(direccion_pais, that.direccion_pais) &&
               Objects.equals(direccion_ciudad, that.direccion_ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccion_pais, direccion_ciudad);
    }
}