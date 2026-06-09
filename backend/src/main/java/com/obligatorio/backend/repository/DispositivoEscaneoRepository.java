package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.DispositivoEscaneo;

public interface DispositivoEscaneoRepository extends JpaRepository<DispositivoEscaneo, Integer> {
    
}