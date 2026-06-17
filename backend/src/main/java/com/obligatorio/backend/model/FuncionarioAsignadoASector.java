package com.obligatorio.backend.model;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario_asignado_a_sector")
public class FuncionarioAsignadoASector {
    @EmbeddedId
    private FuncionarioAsignadoASectorId id;

    @Column(name = "id_dispositivo_escaneo")
    private Integer idDispositivoEscaneo;

    public FuncionarioAsignadoASector() {}

    public FuncionarioAsignadoASectorId getId() { return id; }
    public void setId(FuncionarioAsignadoASectorId id) { this.id = id; }
    public Integer getIdDispositivoEscaneo() { return idDispositivoEscaneo; }
    public void setIdDispositivoEscaneo(Integer idDispositivoEscaneo) { this.idDispositivoEscaneo = idDispositivoEscaneo; }
}