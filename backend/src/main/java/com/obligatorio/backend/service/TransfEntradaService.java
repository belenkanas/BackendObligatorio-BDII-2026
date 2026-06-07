package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.TransfEntrada;
import com.obligatorio.backend.model.TransfEntradaId;
import com.obligatorio.backend.repository.TransfEntradaRepository;

@Service
public class TransfEntradaService {

    @Autowired
    private TransfEntradaRepository transfEntradaRepository;

    public List<TransfEntrada> obtenerTodos() {
        return transfEntradaRepository.findAll();
    }

    public Optional<TransfEntrada> obtenerPorId(TransfEntradaId id) {
        return transfEntradaRepository.findById(id);
    }

    public TransfEntrada crear(TransfEntrada transfEntrada) {
        return transfEntradaRepository.save(transfEntrada);
    }

    public void eliminar(TransfEntradaId id) {
        transfEntradaRepository.deleteById(id);
    }
}