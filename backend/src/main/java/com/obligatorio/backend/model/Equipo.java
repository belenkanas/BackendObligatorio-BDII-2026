package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @Column(name = "nombre_pais")
    private String nombrePais;

    public String getNombrePais() { return nombrePais; }
    
    public void setNombrePais(String nombrePais) { 
        this.nombrePais = nombrePais; 
    }
}