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
import com.obligatorio.backend.service.DispositivoEscaneoService;

@RestController
@RequestMapping("/dispositivos")
@CrossOrigin(origins = "*")
public class DispositivoEscaneoController {
    
    @Autowired
    private DispositivoEscaneoService dispositivoEscaneoService;

    @GetMapping
    public List<DispositivoEscaneo> obtenerTodos() { 
        return dispositivoEscaneoService.obtenerTodos(); 
    }

    @GetMapping("/{id}")
    public Optional<DispositivoEscaneo> obtenerPorId(@PathVariable Integer id) { 
        return dispositivoEscaneoService.obtenerPorId(id); 
    }

    //cargar un nuevo dispositivo con nroSerie único, sin asignar a ningún funcionario
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

    // asignar un dispositivo a un funcionario por su nroLegajo, verificando que el dispositivo no esté ya asignado y que el funcionario exista
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

    // desasignar un dispositivo de su funcionario, dejando el nroLegajo en null
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
    public void eliminar(@PathVariable Integer id) { 
        dispositivoEscaneoService.eliminar(id); 
    }
}