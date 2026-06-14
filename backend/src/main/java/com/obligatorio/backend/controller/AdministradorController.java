package com.obligatorio.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.Administrador;
import com.obligatorio.backend.model.Funcionario;
import com.obligatorio.backend.model.General;
import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.service.AdministradorService;
import com.obligatorio.backend.service.FuncionarioService;
import com.obligatorio.backend.service.GeneralService;
import com.obligatorio.backend.service.PerfilService;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private GeneralService generalService;
    
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping
    public List<Administrador> obtenerTodos() {
        return administradorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> obtenerPorId(@PathVariable Integer id) {
        return administradorService.obtenerPorId(id);
    }

    @PostMapping
    public Administrador crear(@RequestBody Administrador administrador) {
        return administradorService.crear(administrador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        administradorService.eliminar(id);
    }

    @Transactional
    @PostMapping("/cambiar-rol")
    public ResponseEntity<?> cambiarRol(@RequestBody Map<String, Object> datos) {

        Integer idPerfil = (Integer) datos.get("idPerfil");
        String nuevoRol = (String) datos.get("rol");

        Optional<Perfil> perfilOpt = perfilService.obtenerPorId(idPerfil);
        if (perfilOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        long entradasActivas = entradaRepository.countByIdGeneralPropietarioAndEstadoIn(
            idPerfil, List.of("activo", "en_transferencia")
        );
        if (entradasActivas > 0) {
            return ResponseEntity.badRequest().body("El usuario tiene entradas activas o en transferencia, no se puede cambiar el rol");
        }

        // borrar rol anterior
        if (administradorService.obtenerPorId(idPerfil).isPresent()) {
            administradorService.eliminar(idPerfil);
        } else if (funcionarioService.obtenerPorId(idPerfil).isPresent()) {
            funcionarioService.eliminar(idPerfil);
        } else if (generalService.obtenerPorId(idPerfil).isPresent()) {
            generalService.eliminar(idPerfil);
        }

        // crear nuevo rol
        switch (nuevoRol) {
            case "ADMINISTRADOR" -> {
                Administrador admin = new Administrador();
                admin.setPerfil(perfilOpt.get());
                admin.setFecha_asignado(LocalDate.now());
                admin.setPaisSede((String) datos.get("paisSede"));
                administradorService.crear(admin);
            }
            case "FUNCIONARIO" -> {
                Funcionario func = new Funcionario();
                func.setPerfil(perfilOpt.get());
                func.setNroLegajo("LEG-" + idPerfil);
                funcionarioService.crear(func);
            }
            case "GENERAL" -> {
                General general = new General();
                general.setPerfil(perfilOpt.get());
                general.setEstado_verificacion_id("pendiente");
                general.setFecha_registro(LocalDate.now());
                generalService.crear(general);
            }
            default -> {
                return ResponseEntity.badRequest().body("Rol inválido");
            }
        }

        return ResponseEntity.ok("Rol cambiado correctamente");
    }
}
