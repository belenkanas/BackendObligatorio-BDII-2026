package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Funcionario")
public class Funcionario {
    
    @Id
    @Column(name = "id_funcionario")
    private Integer id_funcionario;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_funcionario")
    private Perfil perfil;

    @Column(name = "nroLegajo", unique = true)
    private String nroLegajo;

    // Getters y Setters
    public Integer getId_funcionario() { 
        return id_funcionario; 
    }
    
    public void setId_funcionario(Integer id_funcionario) { 
        this.id_funcionario = id_funcionario; 
    }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public String getNroLegajo() { return nroLegajo; }
    public void setNroLegajo(String nroLegajo) { this.nroLegajo = nroLegajo; }
}
