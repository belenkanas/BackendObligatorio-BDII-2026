package com.obligatorio.backend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EntradaTieneTokenId implements Serializable {
    
    @Column(name = "qr")
    private String qr;

    @Column(name = "id_entrada")
    private Integer idEntrada;

    public EntradaTieneTokenId() {
    }

    public String getQr() { 
        return qr; 
    }
    
    public void setQr(String qr) { 
        this.qr = qr; 
    }
    
    public Integer getIdEntrada() { 
        return idEntrada; 
    }
    
    public void setIdEntrada(Integer idEntrada) { 
        this.idEntrada = idEntrada; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntradaTieneTokenId)) return false;
        EntradaTieneTokenId that = (EntradaTieneTokenId) o;
        return Objects.equals(qr, that.qr) && Objects.equals(idEntrada, that.idEntrada);
    }

    @Override
    public int hashCode() { return Objects.hash(qr, idEntrada); }
}
