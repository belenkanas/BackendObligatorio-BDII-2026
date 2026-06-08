package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.General;
import com.obligatorio.backend.repository.GeneralRepository;

@Service
public class GeneralService {

    @Autowired
    private GeneralRepository generalRepository;

    public List<General> obtenerTodos() {
        return generalRepository.findAll();
    }

    public Optional<General> obtenerPorId(Integer id) {
        return generalRepository.findById(id);
    }

    public General crear(General general) {
        return generalRepository.save(general);
    }

    public void eliminar(Integer id) {
        generalRepository.deleteById(id);
    }
}
