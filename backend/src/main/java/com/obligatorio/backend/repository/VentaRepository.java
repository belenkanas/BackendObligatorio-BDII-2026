package com.obligatorio.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByGeneralIdGeneral(Integer idGeneral);
}