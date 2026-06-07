package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.TransfEntrada;
import com.obligatorio.backend.model.TransfEntradaId;

@Repository
public interface TransfEntradaRepository extends JpaRepository<TransfEntrada, TransfEntradaId> {
}