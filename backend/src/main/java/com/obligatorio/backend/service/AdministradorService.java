package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obligatorio.backend.model.Administrador;
import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.model.Usuario;
import com.obligatorio.backend.repository.AdministradorRepository;
import com.obligatorio.backend.repository.PerfilRepository;
import com.obligatorio.backend.repository.UsuarioRepository;


@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }


    public Optional<Administrador> obtenerPorId(Integer id) {
        return administradorRepository.findById(id);
    }


    public Administrador crear(Administrador administrador) {
        return administradorRepository.save(administrador);
    }


    @Transactional
    public void eliminar(Integer id) {

        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));


        Perfil perfil = administrador.getPerfil();

        Usuario usuario = perfil.getUsuario();


        // 1 - eliminar administrador
        administradorRepository.delete(administrador);


        // 2 - eliminar perfil
        perfilRepository.delete(perfil);


        // 3 - eliminar usuario
        usuarioRepository.delete(usuario);
    }
}