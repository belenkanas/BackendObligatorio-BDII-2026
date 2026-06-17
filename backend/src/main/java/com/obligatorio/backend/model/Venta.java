package com.obligatorio.backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "comision")
    private BigDecimal comision;

    @Column(name = "costo_final")
    private BigDecimal costoFinal;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_general")
    private General general;

    public Integer getIdVenta() { 
        return idVenta; 
    }

    public void setIdVenta(Integer idVenta) { 
        this.idVenta = idVenta; 
    }

    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }

    public void setFechaHora(LocalDateTime fechaHora) { 
        this.fechaHora = fechaHora; 
    }

    public BigDecimal getComision() { 
        return comision; 
    }

    public void setComision(BigDecimal comision) { 
        this.comision = comision; 
    }

    public BigDecimal getCostoFinal() { 
        return costoFinal; 
    }
    
    public void setCostoFinal(BigDecimal costoFinal) { 
        this.costoFinal = costoFinal; 
    }
    
    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) { 
        this.estado = estado; 
    }
    
    public General getGeneral() { 
        return general; 
    }
    
    public void setGeneral(General general) { 
        this.general = general; 
    }
}