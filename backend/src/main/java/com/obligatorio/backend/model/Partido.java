package com.obligatorio.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Partido")
public class Partido {

    @Id
    @Column(name = "fecha_hora")
    private LocalDateTime fecha_hora;

    // Getters y Setters
    public LocalDateTime getFecha_hora() { return fecha_hora; }
    public void setFecha_hora(LocalDateTime fecha_hora) { this.fecha_hora = fecha_hora; }
}