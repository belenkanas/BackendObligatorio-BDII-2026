package com.obligatorio.backend.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public DispositivoEscaneo crear(@RequestBody DispositivoEscaneo disp) { 
        return dispositivoEscaneoService.crear(disp); 
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { 
        dispositivoEscaneoService.eliminar(id); 
    }
}