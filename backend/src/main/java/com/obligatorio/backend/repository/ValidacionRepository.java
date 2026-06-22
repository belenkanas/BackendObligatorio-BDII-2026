package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.obligatorio.backend.model.Validacion;
import com.obligatorio.backend.model.ValidacionId;

@Repository
public interface ValidacionRepository extends JpaRepository<Validacion, ValidacionId> {

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Validacion v WHERE v.id.idDispositivoEscaneo = :idDispositivo")
    boolean existePorIdDispositivo(@Param("idDispositivo") Integer idDispositivo);
}