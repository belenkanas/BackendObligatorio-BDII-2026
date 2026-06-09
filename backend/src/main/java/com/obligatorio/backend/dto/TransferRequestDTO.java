package com.obligatorio.backend.dto;

public class TransferRequestDTO {
    private Integer entradaId;
    private Integer destinatarioId;

    public Integer getEntradaId() { return entradaId; }
    public void setEntradaId(Integer entradaId) { this.entradaId = entradaId; }
    public Integer getDestinatarioId() { return destinatarioId; }
    public void setDestinatarioId(Integer destinatarioId) { this.destinatarioId = destinatarioId; }
}
