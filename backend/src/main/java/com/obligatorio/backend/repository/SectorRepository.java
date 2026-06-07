package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Sector;
import com.obligatorio.backend.model.SectorId;

@Repository
public interface SectorRepository extends JpaRepository<Sector, SectorId> {
}