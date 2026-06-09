package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.AdministradorGestionaEvento;
import com.obligatorio.backend.model.AdministradorGestionaEventoId;

public interface AdministradorGestionaEventoRepository extends JpaRepository<AdministradorGestionaEvento, AdministradorGestionaEventoId> {
}
