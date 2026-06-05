package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.repository.PerfilRepository;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> obtenerTodos() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> obtenerPorId(Integer id) {
        return perfilRepository.findById(id);
    }

    public List<Perfil> obtenerPorUsuario(String mailUsuario) {
        return perfilRepository.findByUsuarioMail(mailUsuario);
    }

    public Perfil crear(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public void eliminar(Integer id) {
        perfilRepository.deleteById(id);
    }
}
