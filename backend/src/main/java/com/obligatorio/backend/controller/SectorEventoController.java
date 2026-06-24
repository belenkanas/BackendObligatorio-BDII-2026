package com.obligatorio.backend.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;
import com.obligatorio.backend.service.SectorEventoService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/sector-eventos")
@CrossOrigin(origins = "*")
public class SectorEventoController {

    @Autowired
    private SectorEventoService sectorEventoService;

    @GetMapping
    public List<SectorEvento> obtenerTodos() {
        return sectorEventoService.obtenerTodos();
    }

    @GetMapping("/{nombreSector}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}/{fechaHoraPartido}")
    public Optional<SectorEvento> obtenerPorId(
            @PathVariable String nombreSector,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad,
            @PathVariable String fechaHoraPartido) {
        SectorEventoId id = new SectorEventoId();
        id.setNombreSector(nombreSector);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        id.setFechaHoraPartido(LocalDateTime.parse(fechaHoraPartido));
        return sectorEventoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> datos) {
        try {
            SectorEvento sectorEvento = new SectorEvento();
            Map<String, Object> idMap = (Map<String, Object>) datos.get("id");
            SectorEventoId id = new SectorEventoId();
            id.setNombreSector((String) idMap.get("nombreSector"));
            id.setEstadioNombre((String) idMap.get("estadioNombre"));
            id.setEstadioDireccionPais((String) idMap.get("estadioDireccionPais"));
            id.setEstadioDireccionCiudad((String) idMap.get("estadioDireccionCiudad"));
            id.setFechaHoraPartido(LocalDateTime.parse((String) idMap.get("fechaHoraPartido")));
            sectorEvento.setId(id);

            if (datos.containsKey("costo") && datos.get("costo") != null) {
                sectorEvento.setCosto(new BigDecimal(datos.get("costo").toString()));
            }

            Integer idAdministrador = Integer.valueOf(datos.get("idAdministrador").toString());
            return ResponseEntity.ok(sectorEventoService.crear(sectorEvento, idAdministrador));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el sector: " + e.getMessage());
        }
    }

    @DeleteMapping("/{nombreSector}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}/{fechaHoraPartido}")
    public void eliminar(
            @PathVariable String nombreSector,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad,
            @PathVariable String fechaHoraPartido) {
        SectorEventoId id = new SectorEventoId();
        id.setNombreSector(nombreSector);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        id.setFechaHoraPartido(LocalDateTime.parse(fechaHoraPartido));
        sectorEventoService.eliminar(id);
    }

    //Deshabilitar sector para un evento específico
    @DeleteMapping("/deshabilitar")
    public ResponseEntity<?> deshabilitar(@RequestBody Map<String, Object> datos) {
        try {
            Map<String, Object> idMap = (Map<String, Object>) datos.get("id");
            SectorEventoId id = new SectorEventoId();
            id.setNombreSector((String) idMap.get("nombreSector"));
            id.setEstadioNombre((String) idMap.get("estadioNombre"));
            id.setEstadioDireccionPais((String) idMap.get("estadioDireccionPais"));
            id.setEstadioDireccionCiudad((String) idMap.get("estadioDireccionCiudad"));
            id.setFechaHoraPartido(LocalDateTime.parse((String) idMap.get("fechaHoraPartido")));

            Integer idAdministrador = Integer.valueOf(datos.get("idAdministrador").toString());
            sectorEventoService.eliminar(id, idAdministrador);
            return ResponseEntity.ok("Sector deshabilitado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo deshabilitar el sector");
        }
    }

    @GetMapping("/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}/{fechaHoraPartido}/{nombrePaisEquipoLocal}/{nombrePaisEquipoVisitante}/sectores")
    public ResponseEntity<?> obtenerPorEvento(
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad,
            @PathVariable String fechaHoraPartido,
            @PathVariable String nombrePaisEquipoLocal,
            @PathVariable String nombrePaisEquipoVisitante) {

        LocalDateTime fecha = LocalDateTime.parse(fechaHoraPartido);

        List<SectorEvento> sectores = sectorEventoService.obtenerPorEvento(
            estadioNombre, estadioDireccionPais, estadioDireccionCiudad, fecha
        );

        return ResponseEntity.ok(sectores);
    }

    // Actualizar el costo de un sector para un evento específico
    @PatchMapping("/actualizar-costo")
    public ResponseEntity<?> actualizarCosto(@RequestBody Map<String, Object> datos) {
        try {
            Map<String, Object> idMap = (Map<String, Object>) datos.get("id");
            SectorEventoId id = new SectorEventoId();
            id.setNombreSector((String) idMap.get("nombreSector"));
            id.setEstadioNombre((String) idMap.get("estadioNombre"));
            id.setEstadioDireccionPais((String) idMap.get("estadioDireccionPais"));
            id.setEstadioDireccionCiudad((String) idMap.get("estadioDireccionCiudad"));
            id.setFechaHoraPartido(LocalDateTime.parse((String) idMap.get("fechaHoraPartido")));

            BigDecimal nuevoCosto = new BigDecimal(datos.get("costo").toString());
            Integer idAdministrador = Integer.valueOf(datos.get("idAdministrador").toString());

            return sectorEventoService.actualizarCosto(id, nuevoCosto, idAdministrador)
                .map(se -> ResponseEntity.ok((Object) se))
                .orElse(ResponseEntity.badRequest().body("Sector no encontrado"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el costo: " + e.getMessage());
        }
    }
}