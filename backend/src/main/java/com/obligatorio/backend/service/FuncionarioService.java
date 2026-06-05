package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Funcionario;
import com.obligatorio.backend.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> obtenerTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> obtenerPorId(Integer id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> obtenerPorNroLegajo(String nroLegajo) {
        return funcionarioRepository.findByNroLegajo(nroLegajo);
    }

    public Funcionario crear(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void eliminar(Integer id) {
        funcionarioRepository.deleteById(id);
    }
}
