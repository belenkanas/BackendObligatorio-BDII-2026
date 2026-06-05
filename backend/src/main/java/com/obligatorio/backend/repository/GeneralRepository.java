package com.obligatorio.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.General;

@Repository
public interface GeneralRepository extends JpaRepository<General, Integer> {
    List<General> findByEstado_verificacion_id(String estado_verificacion_id);
}
