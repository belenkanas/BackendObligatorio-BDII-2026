package com.obligatorio.backend.controller;

import java.util.List;
import java.util.Map;
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

import com.obligatorio.backend.model.DispositivoEscaneo;
import com.obligatorio.backend.repository.ValidacionRepository;
import com.obligatorio.backend.service.DispositivoEscaneoService;

@RestController
@RequestMapping("/dispositivos")
@CrossOrigin(origins = "*")
public class DispositivoEscaneoController {

    @Autowired
    private DispositivoEscaneoService dispositivoEscaneoService;

    @Autowired
    private ValidacionRepository validacionRepository;

    @GetMapping
    public List<DispositivoEscaneo> obtenerTodos() {
        return dispositivoEscaneoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<DispositivoEscaneo> obtenerPorId(@PathVariable Integer id) {
        return dispositivoEscaneoService.obtenerPorId(id);
    }

    @GetMapping("/funcionario/{nroLegajo}")
    public List<DispositivoEscaneo> obtenerPorFuncionario(@PathVariable String nroLegajo) {
        return dispositivoEscaneoService.obtenerPorFuncionario(nroLegajo);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> datos) {
        String nroSerie = (String) datos.get("nroSerie");

        if (nroSerie == null || nroSerie.isBlank()) {
            return ResponseEntity.badRequest().body("El número de serie es obligatorio");
        }

        if (dispositivoEscaneoService.existePorNroSerie(nroSerie)) {
            return ResponseEntity.badRequest().body("Ya existe un dispositivo con ese número de serie");
        }

        DispositivoEscaneo disp = new DispositivoEscaneo();
        disp.setNroSerie(nroSerie);

        DispositivoEscaneo guardado = dispositivoEscaneoService.crear(disp);
        return ResponseEntity.ok(guardado);
    }

    @PostMapping("/{id}/asignar")
    public ResponseEntity<?> asignar(@PathVariable Integer id, @RequestBody Map<String, Object> datos) {
        String nroLegajo = (String) datos.get("nroLegajo");

        if (nroLegajo == null || nroLegajo.isBlank()) {
            return ResponseEntity.badRequest().body("El número de legajo es obligatorio");
        }

        try {
            DispositivoEscaneo actualizado = dispositivoEscaneoService.asignar(id, nroLegajo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/desasignar")
    public ResponseEntity<?> desasignar(@PathVariable Integer id) {
        try {
            DispositivoEscaneo actualizado = dispositivoEscaneoService.desasignar(id);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!dispositivoEscaneoService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.badRequest().body("Dispositivo no encontrado");
        }

        if (validacionRepository.existsByIdIdDispositivoEscaneo(id)) {
            return ResponseEntity.badRequest().body("El dispositivo tiene validaciones registradas, no se puede eliminar");
        }

        dispositivoEscaneoService.eliminar(id);
        return ResponseEntity.ok("Dispositivo eliminado");
    }
}