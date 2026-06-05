package com.obligatorio.backend.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, LocalDateTime> {
}
