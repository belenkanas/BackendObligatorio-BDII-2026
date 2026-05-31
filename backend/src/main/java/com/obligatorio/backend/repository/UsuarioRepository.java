package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // se pueden agregar métodos si es necesario, por ej para buscar por mail
}