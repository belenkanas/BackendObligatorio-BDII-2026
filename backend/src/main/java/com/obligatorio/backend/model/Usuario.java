package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column(name = "mail")
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "documento_tipo")
    private String documentoTipo;

    @Column(name = "documento_numeroDoc")
    private String documentoNumeroDoc;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_numero")
    private String direccionNumero;

    @Column(name = "direccion_codigoPostal")
    private String direccionCodigoPostal;

    @Column(name = "direccion_pais")
    private String direccionPais;

    @Column(name = "direccion_localidad")
    private String direccionLocalidad;

    // Getters y Setters
    public String getMail() { 
        return mail; 
    }

    public void setMail(String mail) { 
        this.mail = mail; 
    }

    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getDocumentoTipo() { 
        return documentoTipo; 
    }

    public void setDocumentoTipo(String documentoTipo) { 
        this.documentoTipo = documentoTipo; 
    }

    public String getDocumentoNumeroDoc() { 
        return documentoNumeroDoc; 
    }

    public void setDocumentoNumeroDoc(String documentoNumeroDoc) { 
        this.documentoNumeroDoc = documentoNumeroDoc; 
    }

    public String getDireccionCalle() { 
        return direccionCalle; 
    }

    public void setDireccionCalle(String direccionCalle) { 
        this.direccionCalle = direccionCalle; 
    }

    public String getDireccionNumero() { 
        return direccionNumero; 
    }

    public void setDireccionNumero(String direccionNumero) { 
        this.direccionNumero = direccionNumero; 
    }

    public String getDireccionCodigoPostal() { 
        return direccionCodigoPostal; 
    }

    public void setDireccionCodigoPostal(String direccionCodigoPostal) { 
        this.direccionCodigoPostal = direccionCodigoPostal; 
    }

    public String getDireccionPais() { 
        return direccionPais; 
    }

    public void setDireccionPais(String direccionPais) { 
        this.direccionPais = direccionPais; 
    }

    public String getDireccionLocalidad() { 
        return direccionLocalidad; 
    }

    public void setDireccionLocalidad(String direccionLocalidad) { 
        this.direccionLocalidad = direccionLocalidad; 
    }
}
