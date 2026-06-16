package com.obligatorio.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "token_escaneado_valido")
public class TokenEscaneadoValido {

    @EmbeddedId
    private TokenEscaneadoValidoId id;

    public TokenEscaneadoValido() {}

    public TokenEscaneadoValidoId getId() { 
        return id; 
    }

    public void setId(TokenEscaneadoValidoId id) { 
        this.id = id; 
    }
}