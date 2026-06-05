package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ValidacionId implements Serializable {

    @Column(name = "nroLegajo_funcionario")
    private String nroLegajoFuncionario;

    @Column(name = "id_dispositivoEscaneo")
    private Integer idDispositivoEscaneo;

    public ValidacionId() {
    }

    public String getNroLegajoFuncionario() {
        return nroLegajoFuncionario;
    }

    public void setNroLegajoFuncionario(String nroLegajoFuncionario) {
        this.nroLegajoFuncionario = nroLegajoFuncionario;
    }

    public Integer getIdDispositivoEscaneo() {
        return idDispositivoEscaneo;
    }

    public void setIdDispositivoEscaneo(Integer idDispositivoEscaneo) {
        this.idDispositivoEscaneo = idDispositivoEscaneo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidacionId)) return false;
        ValidacionId that = (ValidacionId) o;
        return Objects.equals(nroLegajoFuncionario, that.nroLegajoFuncionario)
                && Objects.equals(idDispositivoEscaneo, that.idDispositivoEscaneo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroLegajoFuncionario, idDispositivoEscaneo);
    }
}