package com.obligatorio.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.FuncionarioAsignadoASector;
import com.obligatorio.backend.model.FuncionarioAsignadoASectorId;
import com.obligatorio.backend.service.FuncionarioAsignadoASectorService;

@RestController
@RequestMapping("/funcionarios-asignados-sector")
@CrossOrigin(origins = "*")
public class FuncionarioAsignadoASectorController {

    @Autowired
    private FuncionarioAsignadoASectorService funcionarioAsignadoASectorService;

    @GetMapping
    public List<FuncionarioAsignadoASector> obtenerTodos() {
        return funcionarioAsignadoASectorService.obtenerTodos();
    }

    @GetMapping("/{nroLegajo}/{nombreSector}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}")
    public Optional<FuncionarioAsignadoASector> obtenerPorId(
            @PathVariable String nroLegajo,
            @PathVariable String nombreSector,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad) {
        FuncionarioAsignadoASectorId id = new FuncionarioAsignadoASectorId();
        id.setNroLegajo(nroLegajo);
        id.setNombreSector(nombreSector);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        return funcionarioAsignadoASectorService.obtenerPorId(id);
    }

    @PostMapping
    public FuncionarioAsignadoASector crear(@RequestBody FuncionarioAsignadoASector funcionarioAsignadoASector) {
        return funcionarioAsignadoASectorService.crear(funcionarioAsignadoASector);
    }

    @DeleteMapping("/{nroLegajo}/{nombreSector}/{estadioNombre}/{estadioDireccionPais}/{estadioDireccionCiudad}")
    public void eliminar(
            @PathVariable String nroLegajo,
            @PathVariable String nombreSector,
            @PathVariable String estadioNombre,
            @PathVariable String estadioDireccionPais,
            @PathVariable String estadioDireccionCiudad) {
        FuncionarioAsignadoASectorId id = new FuncionarioAsignadoASectorId();
        id.setNroLegajo(nroLegajo);
        id.setNombreSector(nombreSector);
        id.setEstadioNombre(estadioNombre);
        id.setEstadioDireccionPais(estadioDireccionPais);
        id.setEstadioDireccionCiudad(estadioDireccionCiudad);
        funcionarioAsignadoASectorService.eliminar(id);
    }

    @DeleteMapping("/desasignar")
    public ResponseEntity<?> desasignar(@RequestBody FuncionarioAsignadoASector asignacion) {
        try {
            funcionarioAsignadoASectorService.eliminar(asignacion.getId());
            return ResponseEntity.ok("Asignación eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo eliminar la asignación");
        }
    }
}