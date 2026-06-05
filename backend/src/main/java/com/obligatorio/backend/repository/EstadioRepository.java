package com.obligatorio.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Estadio;
import com.obligatorio.backend.model.EstadioId;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, EstadioId> {
    List<Estadio> findByIdDireccion_pais(String pais);
}
