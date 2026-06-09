package com.obligatorio.backend.dto;

import java.util.List;

public class CompraRequestDTO {
    private String mailUsuario;
    private List<Integer> entradasIds;

    public String getMailUsuario() { return mailUsuario; }
    public void setMailUsuario(String mailUsuario) { this.mailUsuario = mailUsuario; }
    public List<Integer> getEntradasIds() { return entradasIds; }
    public void setEntradasIds(List<Integer> entradasIds) { this.entradasIds = entradasIds; }
}
