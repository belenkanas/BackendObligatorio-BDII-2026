package com.obligatorio.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.dto.CrearEventoRequest;
import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;
import com.obligatorio.backend.service.EventoService;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> obtenerTodos() { return eventoService.obtenerTodos(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearEventoRequest datos) {
        if (datos.getIdAdministrador() == null) {
            return ResponseEntity.badRequest().body("El id del administrador es obligatorio");
        }
        Evento evento = new Evento();
        evento.setId(datos.getId());
        Evento creado = eventoService.crear(evento, datos.getIdAdministrador());
        return ResponseEntity.ok(creado);
    }

    // Endpoint para suspender un evento (cambiar su estado a inactivo)
    @PatchMapping("/actualizar-estado")
    public ResponseEntity<?> actualizarEstado(@RequestBody Map<String, Object> datos) {
        try {
            Map<String, Object> idMap = (Map<String, Object>) datos.get("id");
            EventoId id = new EventoId();
            id.setEstadioNombre((String) idMap.get("estadioNombre"));
            id.setEstadioDireccionPais((String) idMap.get("estadioDireccionPais"));
            id.setEstadioDireccionCiudad((String) idMap.get("estadioDireccionCiudad"));
            id.setFechaHoraPartido(LocalDateTime.parse((String) idMap.get("fechaHoraPartido")));
            id.setNombrePaisEquipoLocal((String) idMap.get("nombrePaisEquipoLocal"));
            id.setNombrePaisEquipoVisitante((String) idMap.get("nombrePaisEquipoVisitante"));

            String nuevoEstado = (String) datos.get("estado");
            if (!nuevoEstado.equals("activo") && !nuevoEstado.equals("suspendido")) {
                return ResponseEntity.badRequest().body("Estado inválido. Debe ser 'activo' o 'suspendido'");
            }

            return eventoService.actualizarEstado(id, nuevoEstado)
                .map(e -> ResponseEntity.ok((Object) e))
                .orElse(ResponseEntity.badRequest().body("Evento no encontrado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el estado: " + e.getMessage());
        }
    }

}