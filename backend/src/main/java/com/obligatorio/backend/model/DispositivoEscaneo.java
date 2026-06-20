// model/DispositivoEscaneo.java
package com.obligatorio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dispositivo_escaneo")
public class DispositivoEscaneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    // Numero de legajo del funcionario asignado a este dispositivo. Que es foreign key a funcionario(nro_legajo)
    @Column(name = "nro_legajo")
    private String nroLegajo;
    public String getNroLegajo() { return nroLegajo; }
    public void setNroLegajo(String nroLegajo) { this.nroLegajo = nroLegajo; }
}