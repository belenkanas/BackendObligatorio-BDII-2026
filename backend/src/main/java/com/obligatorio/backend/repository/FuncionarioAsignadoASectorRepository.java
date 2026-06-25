package com.obligatorio.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.FuncionarioAsignadoASector;
import com.obligatorio.backend.model.FuncionarioAsignadoASectorId;

@Repository
public interface FuncionarioAsignadoASectorRepository extends JpaRepository<FuncionarioAsignadoASector, FuncionarioAsignadoASectorId> {

    List<FuncionarioAsignadoASector> findByIdNroLegajo(String nroLegajo);

    @Query("""
        SELECT COUNT(f)
        FROM FuncionarioAsignadoASector f
        WHERE f.id.estadioNombre = :estadio
        AND f.id.estadioDireccionPais = :pais
        AND f.id.estadioDireccionCiudad = :ciudad
        AND f.id.fechaHoraPartido = :fecha
    """)
    
    long countFuncionariosPorEvento(
        String estadioNombre,
        String estadioDireccionPais,
        String estadioDireccionCiudad,
        LocalDateTime fechaHoraPartido
    );

    @Query("""
        SELECT COUNT(f) > 0
        FROM FuncionarioAsignadoASector f
        WHERE f.id.nroLegajo = :nroLegajo
        AND f.id.nombreSector = :nombreSector
        AND f.id.estadioNombre = :estadioNombre
        AND f.id.estadioDireccionPais = :estadioDireccionPais
        AND f.id.estadioDireccionCiudad = :estadioDireccionCiudad
        AND f.id.fechaHoraPartido = :fechaHoraPartido
    """)
    boolean existeAsignacion(
        @Param("nroLegajo") String nroLegajo,
        @Param("nombreSector") String nombreSector,
        @Param("estadioNombre") String estadioNombre,
        @Param("estadioDireccionPais") String estadioDireccionPais,
        @Param("estadioDireccionCiudad") String estadioDireccionCiudad,
        @Param("fechaHoraPartido") LocalDateTime fechaHoraPartido
    );
}