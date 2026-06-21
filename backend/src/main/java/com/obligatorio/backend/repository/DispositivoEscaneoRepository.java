package com.obligatorio.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.DispositivoEscaneo;

public interface DispositivoEscaneoRepository extends JpaRepository<DispositivoEscaneo, Integer> {
    boolean existsByNroSerie(String nroSerie);
    List<DispositivoEscaneo> findByNroLegajo(String nroLegajo);
}