package com.obligatorio.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;

@Repository
public interface SectorEventoRepository extends JpaRepository<SectorEvento, SectorEventoId> {

    @Query("SELECT se FROM SectorEvento se WHERE se.id.estadioNombre = :estadio AND se.id.estadioDireccionPais = :pais AND se.id.estadioDireccionCiudad = :ciudad AND se.id.fechaHoraPartido = :fecha")
    List<SectorEvento> findByEvento(
        @Param("estadio") String estadio,
        @Param("pais") String pais,
        @Param("ciudad") String ciudad,
        @Param("fecha") LocalDateTime fecha
    );
}