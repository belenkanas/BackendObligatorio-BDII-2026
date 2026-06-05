package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, String> {
}
