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

import com.obligatorio.backend.model.Venta;
import com.obligatorio.backend.service.VentaService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> obtenerTodos() { return ventaService.obtenerTodos(); }

    @GetMapping("/{id}")
    public Optional<Venta> obtenerPorId(@PathVariable Integer id) { return ventaService.obtenerPorId(id); }

    @GetMapping("/usuario/{idGeneral}")
    public List<Venta> obtenerPorGeneral(@PathVariable Integer idGeneral) { return ventaService.obtenerPorGeneral(idGeneral); }

    @PostMapping
    public Venta crear(@RequestBody Venta venta) { return ventaService.crear(venta); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { ventaService.eliminar(id); }
}
