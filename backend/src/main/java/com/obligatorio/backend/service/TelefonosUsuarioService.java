package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.TelefonosUsuario;
import com.obligatorio.backend.model.TelefonosUsuarioId;
import com.obligatorio.backend.repository.TelefonosUsuarioRepository;

@Service
public class TelefonosUsuarioService {

    @Autowired
    private TelefonosUsuarioRepository telefonosUsuarioRepository;

    public List<TelefonosUsuario> obtenerTodos() {
        return telefonosUsuarioRepository.findAll();
    }

    public Optional<TelefonosUsuario> obtenerPorId(TelefonosUsuarioId id) {
        return telefonosUsuarioRepository.findById(id);
    }

    public TelefonosUsuario crear(TelefonosUsuario telefonosUsuario) {
        return telefonosUsuarioRepository.save(telefonosUsuario);
    }

    public void eliminar(TelefonosUsuarioId id) {
        telefonosUsuarioRepository.deleteById(id);
    }
}