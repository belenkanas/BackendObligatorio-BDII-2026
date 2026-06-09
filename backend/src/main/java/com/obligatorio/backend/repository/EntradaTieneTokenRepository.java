package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.EntradaTieneToken;
import com.obligatorio.backend.model.EntradaTieneTokenId;

public interface EntradaTieneTokenRepository extends JpaRepository<EntradaTieneToken, EntradaTieneTokenId> {
}