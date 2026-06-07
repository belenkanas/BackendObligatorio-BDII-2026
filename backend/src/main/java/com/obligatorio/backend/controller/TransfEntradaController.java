package com.obligatorio.backend.controller;

import java.time.LocalDateTime;
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

import com.obligatorio.backend.model.TransfEntrada;
import com.obligatorio.backend.model.TransfEntradaId;
import com.obligatorio.backend.service.TransfEntradaService;

@RestController
@RequestMapping("/transferencias-entrada")
@CrossOrigin(origins = "*")
public class TransfEntradaController {

    @Autowired
    private TransfEntradaService transfEntradaService;

    @GetMapping
    public List<TransfEntrada> obtenerTodos() {
        return transfEntradaService.obtenerTodos();
    }

    @GetMapping("/{fechaHora}/{idEntrada}")
    public Optional<TransfEntrada> obtenerPorId(
            @PathVariable String fechaHora,
            @PathVariable Integer idEntrada) {
        TransfEntradaId id = new TransfEntradaId();
        id.setFechaHora(LocalDateTime.parse(fechaHora));
        id.setIdEntrada(idEntrada);
        return transfEntradaService.obtenerPorId(id);
    }

    @PostMapping
    public TransfEntrada crear(@RequestBody TransfEntrada transfEntrada) {
        return transfEntradaService.crear(transfEntrada);
    }

    @DeleteMapping("/{fechaHora}/{idEntrada}")
    public void eliminar(
            @PathVariable String fechaHora,
            @PathVariable Integer idEntrada) {
        TransfEntradaId id = new TransfEntradaId();
        id.setFechaHora(LocalDateTime.parse(fechaHora));
        id.setIdEntrada(idEntrada);
        transfEntradaService.eliminar(id);
    }
}