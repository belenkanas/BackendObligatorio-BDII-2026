package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.TokenEscaneadoValido;
import com.obligatorio.backend.model.TokenEscaneadoValidoId;

public interface TokenEscaneadoValidoRepository extends JpaRepository<TokenEscaneadoValido, TokenEscaneadoValidoId> {
}