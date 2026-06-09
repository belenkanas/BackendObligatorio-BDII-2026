package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Token")
public class Token {

    @Id
    @Column(name = "qr")
    private String qr;

    @Column(name = "valido")
    private Boolean valido;

    public String getQr() { 
        return qr; 
    }

    public void setQr(String qr) { 
        this.qr = qr; 
    }

    public Boolean getValido() { 
        return valido; 
    }

    public void setValido(Boolean valido) { 
        this.valido = valido; 
    }
}