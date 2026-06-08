package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.General;

@Repository
public interface GeneralRepository extends JpaRepository<General, Integer> {
}
