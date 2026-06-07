package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.FuncionarioAsignadoASector;
import com.obligatorio.backend.model.FuncionarioAsignadoASectorId;

@Repository
public interface FuncionarioAsignadoASectorRepository
        extends JpaRepository<FuncionarioAsignadoASector, FuncionarioAsignadoASectorId> {
}