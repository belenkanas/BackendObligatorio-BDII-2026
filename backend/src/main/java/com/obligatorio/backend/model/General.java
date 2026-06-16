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
@Table(name = "general")
public class General {
    
    @Id
    @Column(name = "id_general")
    private Integer id_general;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_general")
    private Perfil perfil;
    
    @Column(name = "estado_verificacion_id")
    private String estado_verificacion_id;

    @Column(name = "fecha_registro")
    private LocalDate fecha_registro;

    public Integer getId_general() { 
        return id_general; 
    }

    public void setId_general(Integer id_general) { 
        this.id_general = id_general; 
    }

    public Perfil getPerfil() { 
        return perfil; 
    }

    public void setPerfil(Perfil perfil) { 
        this.perfil = perfil; 
    }

    public String getEstado_verificacion_id() { 
        return estado_verificacion_id; 
    }

    public void setEstado_verificacion_id(String estado_verificacion_id) { 
        this.estado_verificacion_id = estado_verificacion_id; 
    }

    public LocalDate getFecha_registro() { 
        return fecha_registro; 
    }

    public void setFecha_registro(LocalDate fecha_registro) { 
        this.fecha_registro = fecha_registro; 
    }
}
