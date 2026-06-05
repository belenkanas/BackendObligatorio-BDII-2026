package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
