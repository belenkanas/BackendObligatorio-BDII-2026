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

import com.obligatorio.backend.model.Funcionario;
import com.obligatorio.backend.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> obtenerTodos() {
        return funcionarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> obtenerPorId(@PathVariable Integer id) {
        return funcionarioService.obtenerPorId(id);
    }

    @GetMapping("/legajo/{nroLegajo}")
    public Optional<Funcionario> obtenerPorNroLegajo(@PathVariable String nroLegajo) {
        return funcionarioService.obtenerPorNroLegajo(nroLegajo);
    }

    @PostMapping
    public Funcionario crear(@RequestBody Funcionario funcionario) {
        return funcionarioService.crear(funcionario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        funcionarioService.eliminar(id);
    }
}
