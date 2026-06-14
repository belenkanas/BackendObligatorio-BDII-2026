package com.obligatorio.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.TransfEntrada;
import com.obligatorio.backend.model.TransfEntradaId;

@Repository
public interface TransfEntradaRepository extends JpaRepository<TransfEntrada, TransfEntradaId> {
    
    List<TransfEntrada> findByIdGeneralRecibeAndEstado(Integer idGeneralRecibe, String estado);
    
    List<TransfEntrada> findByIdGeneralRealizaOrIdGeneralRecibe(Integer idGeneralRealiza, Integer idGeneralRecibe);

    List<TransfEntrada> findByEstado(String estado);
}