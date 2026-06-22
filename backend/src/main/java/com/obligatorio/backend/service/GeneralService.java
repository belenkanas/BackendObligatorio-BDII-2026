package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obligatorio.backend.model.General;
import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.model.Usuario;
import com.obligatorio.backend.repository.GeneralRepository;
import com.obligatorio.backend.repository.PerfilRepository;
import com.obligatorio.backend.repository.UsuarioRepository;


@Service
public class GeneralService {

    @Autowired
    private GeneralRepository generalRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<General> obtenerTodos() {
        return generalRepository.findAll();
    }


    public Optional<General> obtenerPorId(Integer id) {
        return generalRepository.findById(id);
    }


    public General crear(General general) {
        return generalRepository.save(general);
    }


    @Transactional
    public void eliminar(Integer id) {

        General general = generalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("General no encontrado"));


        Perfil perfil = general.getPerfil();

        Usuario usuario = perfil.getUsuario();


        // eliminar general
        generalRepository.delete(general);


        // eliminar perfil
        perfilRepository.delete(perfil);


        // eliminar usuario
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void eliminarSoloRol(Integer id) {
        General general = generalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("General no encontrado"));
        generalRepository.delete(general);
    }
}