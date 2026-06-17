package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obligatorio.backend.model.Funcionario;
import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.model.Usuario;
import com.obligatorio.backend.repository.FuncionarioRepository;
import com.obligatorio.backend.repository.PerfilRepository;
import com.obligatorio.backend.repository.UsuarioRepository;


@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


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


    @Transactional
    public void eliminar(Integer id) {

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario no encontrado"));


        Perfil perfil = funcionario.getPerfil();

        Usuario usuario = perfil.getUsuario();


        // eliminar funcionario
        funcionarioRepository.delete(funcionario);


        // eliminar perfil
        perfilRepository.delete(perfil);


        // eliminar usuario
        usuarioRepository.delete(usuario);
    }
}