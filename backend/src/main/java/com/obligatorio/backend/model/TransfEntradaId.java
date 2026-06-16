package com.obligatorio.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TransfEntradaId implements Serializable {

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "id_entrada")
    private Integer idEntrada;

    public TransfEntradaId() {
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
        if (!(o instanceof TransfEntradaId)) return false;
        TransfEntradaId that = (TransfEntradaId) o;
        return Objects.equals(fechaHora, that.fechaHora)
                && Objects.equals(idEntrada, that.idEntrada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora, idEntrada);
    }
}