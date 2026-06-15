package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.FuncionarioAsignadoASector;
import com.obligatorio.backend.model.FuncionarioAsignadoASectorId;
import com.obligatorio.backend.repository.FuncionarioAsignadoASectorRepository;

@Service
public class FuncionarioAsignadoASectorService {

    @Autowired
    private FuncionarioAsignadoASectorRepository funcionarioAsignadoASectorRepository;

    public List<FuncionarioAsignadoASector> obtenerTodos() {
        return funcionarioAsignadoASectorRepository.findAll();
    }

    public Optional<FuncionarioAsignadoASector> obtenerPorId(FuncionarioAsignadoASectorId id) {
        return funcionarioAsignadoASectorRepository.findById(id);
    }

    public FuncionarioAsignadoASector crear(FuncionarioAsignadoASector funcionarioAsignadoASector) {
        return funcionarioAsignadoASectorRepository.save(funcionarioAsignadoASector);
    }

    public void eliminar(FuncionarioAsignadoASectorId id) {
        funcionarioAsignadoASectorRepository.deleteById(id);
    }

    public List<FuncionarioAsignadoASector> obtenerPorLegajo(String legajo) {
        return funcionarioAsignadoASectorRepository.findByIdNroLegajo(legajo);
}
}