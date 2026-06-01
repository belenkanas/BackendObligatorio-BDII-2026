package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Equipo")
public class Equipo {

    @Id
    @Column(name = "nombrePais")
    private String nombrePais;

    // Getters y Setters
    public String getNombrePais() { return nombrePais; }
    public void setNombrePais(String nombrePais) { this.nombrePais = nombrePais; }
}