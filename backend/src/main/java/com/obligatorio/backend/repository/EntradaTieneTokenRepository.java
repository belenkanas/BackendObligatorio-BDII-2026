package com.obligatorio.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.EntradaTieneToken;
import com.obligatorio.backend.model.EntradaTieneTokenId;


public interface EntradaTieneTokenRepository extends JpaRepository<EntradaTieneToken, EntradaTieneTokenId> {

    Optional<EntradaTieneToken> findByIdIdEntrada(Integer idEntrada);
    void deleteByIdIdEntrada(Integer idEntrada);
    Optional<EntradaTieneToken> findByIdQr(String qr);
}