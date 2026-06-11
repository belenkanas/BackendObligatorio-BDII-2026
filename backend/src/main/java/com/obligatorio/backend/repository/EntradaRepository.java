package com.obligatorio.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

    @Query("SELECT COUNT(e) FROM Entrada e WHERE e.nombreSector = :sector AND e.estadioNombre = :estadio AND e.estadioDireccionPais = :pais AND e.estadioDireccionCiudad = :ciudad AND e.fechaHoraPartido = :fecha AND e.estado != 'vencido'")
    long countBySectorEvento(
        @Param("sector") String sector,
        @Param("estadio") String estadio,
        @Param("pais") String pais,
        @Param("ciudad") String ciudad,
        @Param("fecha") LocalDateTime fecha
    );

    List<Entrada> findByIdGeneralPropietario(Integer idGeneralPropietario);

    List<Entrada> findByIdVenta(Integer idVenta);
}