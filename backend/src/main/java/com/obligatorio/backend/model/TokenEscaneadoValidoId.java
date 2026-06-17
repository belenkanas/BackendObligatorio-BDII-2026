package com.obligatorio.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TokenEscaneadoValidoId implements Serializable {

    @Column(name = "nro_legajo_funcionario")
    private String nroLegajoFuncionario;

    @Column(name = "id_dispositivo_escaneo")
    private Integer idDispositivoEscaneo;

    @Column(name = "fecha_validacion")
    private LocalDateTime fechaValidacion;

    @Column(name = "qr_token")
    private String qrToken;

    public TokenEscaneadoValidoId() {}

    public String getNroLegajoFuncionario() { return nroLegajoFuncionario; }
    public void setNroLegajoFuncionario(String nroLegajoFuncionario) { 
        this.nroLegajoFuncionario = nroLegajoFuncionario; 
    }

    public Integer getIdDispositivoEscaneo() { return idDispositivoEscaneo; }
    public void setIdDispositivoEscaneo(Integer idDispositivoEscaneo) { 
        this.idDispositivoEscaneo = idDispositivoEscaneo; 
    }

    public LocalDateTime getFechaValidacion() { return fechaValidacion; }
    public void setFechaValidacion(LocalDateTime fechaValidacion) { 
        this.fechaValidacion = fechaValidacion; 
    }

    public String getQrToken() { return qrToken; }
    public void setQrToken(String qrToken) { 
        this.qrToken = qrToken; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenEscaneadoValidoId)) return false;
        TokenEscaneadoValidoId that = (TokenEscaneadoValidoId) o;
        return Objects.equals(nroLegajoFuncionario, that.nroLegajoFuncionario) &&
               Objects.equals(idDispositivoEscaneo, that.idDispositivoEscaneo) &&
               Objects.equals(fechaValidacion, that.fechaValidacion) &&
               Objects.equals(qrToken, that.qrToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroLegajoFuncionario, idDispositivoEscaneo, fechaValidacion, qrToken);
    }
}