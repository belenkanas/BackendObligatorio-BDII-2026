package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;

@Repository
public interface SectorEventoRepository extends JpaRepository<SectorEvento, SectorEventoId> {
}