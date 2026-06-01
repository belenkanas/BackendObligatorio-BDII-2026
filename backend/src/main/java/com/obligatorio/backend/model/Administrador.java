package com.obligatorio.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administrador")
public class Administrador {
    
    @Id
    @Column(name = "id_administrador")
    private Integer id_administrador;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_administrador")
    private Perfil perfil;

    @Column(name = "fecha_asignado")
    private LocalDate fecha_asignado;

    @Column(name = "paisSede")
    private String paisSede;

    // Getters y Setters
    public Integer getId_administrador() { 
        return id_administrador; 
    }

    public void setId_administrador(Integer id_administrador) { 
        this.id_administrador = id_administrador; 
    }

    public Perfil getPerfil() { 
        return perfil; 
    }

    public void setPerfil(Perfil perfil) { 
        this.perfil = perfil; 
    }

    public LocalDate getFecha_asignado() { 
        return fecha_asignado; 
    }

    public void setFecha_asignado(LocalDate fecha_asignado) { 
        this.fecha_asignado = fecha_asignado; 
    }

    public String getPaisSede() { 
        return paisSede; 
    }

    public void setPaisSede(String paisSede) { 
        this.paisSede = paisSede; 
    }
}
