package com.obligatorio.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.obligatorio.backend.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT v FROM Venta v WHERE v.general.id_general = :idGeneral")
    List<Venta> findByIdGeneral(@Param("idGeneral") Integer idGeneral);
}