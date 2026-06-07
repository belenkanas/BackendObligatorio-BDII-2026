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

import com.obligatorio.backend.model.Validacion;
import com.obligatorio.backend.model.ValidacionId;
import com.obligatorio.backend.service.ValidacionService;

@RestController
@RequestMapping("/validaciones")
@CrossOrigin(origins = "*")
public class ValidacionController {

    @Autowired
    private ValidacionService validacionService;

    @GetMapping
    public List<Validacion> obtenerTodos() {
        return validacionService.obtenerTodos();
    }

    @GetMapping("/{nroLegajoFuncionario}/{idDispositivoEscaneo}")
    public Optional<Validacion> obtenerPorId(
            @PathVariable String nroLegajoFuncionario,
            @PathVariable Integer idDispositivoEscaneo) {
        ValidacionId id = new ValidacionId();
        id.setNroLegajoFuncionario(nroLegajoFuncionario);
        id.setIdDispositivoEscaneo(idDispositivoEscaneo);
        return validacionService.obtenerPorId(id);
    }

    @PostMapping
    public Validacion crear(@RequestBody Validacion validacion) {
        return validacionService.crear(validacion);
    }

    @DeleteMapping("/{nroLegajoFuncionario}/{idDispositivoEscaneo}")
    public void eliminar(
            @PathVariable String nroLegajoFuncionario,
            @PathVariable Integer idDispositivoEscaneo) {
        ValidacionId id = new ValidacionId();
        id.setNroLegajoFuncionario(nroLegajoFuncionario);
        id.setIdDispositivoEscaneo(idDispositivoEscaneo);
        validacionService.eliminar(id);
    }
}