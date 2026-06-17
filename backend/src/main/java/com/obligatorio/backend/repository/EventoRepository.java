package com.obligatorio.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, EventoId> {
    @Query("SELECT e FROM Evento e WHERE e.id.estadioNombre = :estadioNombre AND e.id.estadioDireccionPais = :pais AND e.id.estadioDireccionCiudad = :ciudad")
    List<Evento> findByEstadio(@Param("estadioNombre") String estadioNombre, @Param("pais") String pais, @Param("ciudad") String ciudad);
}