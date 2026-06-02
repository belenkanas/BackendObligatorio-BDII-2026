package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TelefonosUsuarioId implements Serializable {

    @Column(name = "mail_usuario")
    private String mailUsuario;

    @Column(name = "telefono")
    private String telefono;

    public TelefonosUsuarioId() {
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public void setMailUsuario(String mailUsuario) {
        this.mailUsuario = mailUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefonosUsuarioId)) return false;
        TelefonosUsuarioId that = (TelefonosUsuarioId) o;
        return Objects.equals(mailUsuario, that.mailUsuario)
                && Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailUsuario, telefono);
    }
}