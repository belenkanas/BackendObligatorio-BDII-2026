package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.TelefonosUsuario;
import com.obligatorio.backend.model.TelefonosUsuarioId;

@Repository
public interface TelefonosUsuarioRepository extends JpaRepository<TelefonosUsuario, TelefonosUsuarioId> {
}