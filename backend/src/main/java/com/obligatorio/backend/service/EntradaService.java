package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.repository.EntradaRepository;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public List<Entrada> obtenerTodos() {
        return entradaRepository.findAll();
    }

    public Optional<Entrada> obtenerPorId(Integer id) {
        return entradaRepository.findById(id);
    }

    public Entrada crear(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public void eliminar(Integer id) {
        entradaRepository.deleteById(id);
    }

    public List<Entrada> obtenerPorPropietario(Integer idGeneral) {
        return entradaRepository.findByIdGeneralPropietario(idGeneral);
    }

    public List<Entrada> obtenerPorVenta(Integer idVenta) {
        return entradaRepository.findByIdVenta(idVenta);
    }
}