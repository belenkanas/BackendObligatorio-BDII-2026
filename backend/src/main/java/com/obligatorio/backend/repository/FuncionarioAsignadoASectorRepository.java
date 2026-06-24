package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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
}