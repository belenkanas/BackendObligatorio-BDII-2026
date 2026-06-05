package com.obligatorio.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNroLegajo(String nroLegajo);
}
