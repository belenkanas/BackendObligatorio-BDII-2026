package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TransfEntrada")
public class TransfEntrada {

    @EmbeddedId
    private TransfEntradaId id;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_general_realiza")
    private Integer idGeneralRealiza;

    @Column(name = "id_general_recibe")
    private Integer idGeneralRecibe;

    public TransfEntrada() {
    }

    public TransfEntradaId getId() {
        return id;
    }

    public void setId(TransfEntradaId id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdGeneralRealiza() {
        return idGeneralRealiza;
    }

    public void setIdGeneralRealiza(Integer idGeneralRealiza) {
        this.idGeneralRealiza = idGeneralRealiza;
    }

    public Integer getIdGeneralRecibe() {
        return idGeneralRecibe;
    }

    public void setIdGeneralRecibe(Integer idGeneralRecibe) {
        this.idGeneralRecibe = idGeneralRecibe;
    }
}