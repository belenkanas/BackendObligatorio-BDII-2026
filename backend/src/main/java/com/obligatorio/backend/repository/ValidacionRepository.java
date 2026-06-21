package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Validacion;
import com.obligatorio.backend.model.ValidacionId;

@Repository
public interface ValidacionRepository extends JpaRepository<Validacion, ValidacionId> {
    boolean existsByIdIdDispositivoEscaneo(Integer idDispositivoEscaneo);
}