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

import com.obligatorio.backend.model.General;
import com.obligatorio.backend.service.GeneralService;

@RestController
@RequestMapping("/generales")
@CrossOrigin(origins = "*")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @GetMapping
    public List<General> obtenerTodos() {
        return generalService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<General> obtenerPorId(@PathVariable Integer id) {
        return generalService.obtenerPorId(id);
    }

    @PostMapping
    public General crear(@RequestBody General general) {
        return generalService.crear(general);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        generalService.eliminar(id);
    }
}
