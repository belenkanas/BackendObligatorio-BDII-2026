package com.obligatorio.backend.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;

public interface EventoRepository extends JpaRepository<Evento, EventoId> {
    @Query("SELECT e FROM Evento e WHERE e.id.estadioNombre = :estadioNombre AND e.id.estadioDireccionPais = :pais AND e.id.estadioDireccionCiudad = :ciudad")
    List<Evento> findByEstadio(@Param("estadioNombre") String estadioNombre, @Param("pais") String pais, @Param("ciudad") String ciudad);

    @Query("SELECT e FROM Evento e WHERE e.id.estadioNombre = :estadioNombre AND e.id.estadioDireccionPais = :pais AND e.id.estadioDireccionCiudad = :ciudad AND e.id.fechaHoraPartido = :fecha")
    Optional<Evento> findByEstadioYFecha(
        @Param("estadioNombre") String estadioNombre,
        @Param("pais") String pais,
        @Param("ciudad") String ciudad,
        @Param("fecha") LocalDateTime fecha
    );
}