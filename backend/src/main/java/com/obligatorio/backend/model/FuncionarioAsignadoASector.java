package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Funcionario_asignadoA_Sector")
public class FuncionarioAsignadoASector {

    @EmbeddedId
    private FuncionarioAsignadoASectorId id;

    public FuncionarioAsignadoASector() {
    }

    public FuncionarioAsignadoASectorId getId() {
        return id;
    }

    public void setId(FuncionarioAsignadoASectorId id) {
        this.id = id;
    }
}