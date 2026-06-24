package com.obligatorio.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.obligatorio.backend.model.Usuario;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.FuncionarioAsignadoASectorRepository;
import com.obligatorio.backend.repository.GeneralRepository;
import com.obligatorio.backend.repository.VentaRepository;
import com.obligatorio.backend.service.AdministradorService;
import com.obligatorio.backend.service.FuncionarioService;
import com.obligatorio.backend.service.GeneralService;
import com.obligatorio.backend.service.PerfilService;
import com.obligatorio.backend.service.UsuarioService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private FuncionarioAsignadoASectorRepository funcionarioAsignadoASectorRepository;

    @Autowired
    private GeneralRepository generalRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<Administrador> obtenerTodos() {
        return administradorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> obtenerPorId(@PathVariable Integer id) {
        return administradorService.obtenerPorId(id);
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

        // validar que el usuario actual general no tenga entradas activas
        if (generalService.obtenerPorId(idPerfil).isPresent()) {
            long entradasActivas = entradaRepository.countByIdGeneralPropietarioAndEstadoIn(
                idPerfil, List.of("activa", "en_transferencia")
            );

            if (entradasActivas > 0) {
                return ResponseEntity.badRequest().body("El usuario tiene entradas activas o en transferencia, no se puede cambiar el rol");
            }
        }

        // validar que general no tenga ventas asociadas
        if (ventaRepository.existsByIdGeneral(idPerfil)) {
            return ResponseEntity.badRequest().body("El usuario tiene ventas asociadas, no se puede cambiar el rol");
        }

        // validar que el usuario actual funcionario no tenga sectores asignados
        if (funcionarioService.obtenerPorId(idPerfil).isPresent()) {
            Optional<Funcionario> funcionarioOpt = funcionarioService.obtenerPorId(idPerfil);
            if (funcionarioOpt.isPresent()) {
                String nroLegajo = funcionarioOpt.get().getNroLegajo();
                long sectoresAsignados = funcionarioAsignadoASectorRepository.findByIdNroLegajo(nroLegajo).size();
                if (sectoresAsignados > 0) {
                    return ResponseEntity.badRequest().body("El usuario tiene sectores asignados, no se puede cambiar el rol");
                }
            }
        }

        // borrar solo el rol anterior (sin tocar Perfil ni Usuario)
        if (administradorService.obtenerPorId(idPerfil).isPresent()) {
            administradorService.eliminarSoloRol(idPerfil);
        } else if (funcionarioService.obtenerPorId(idPerfil).isPresent()) {
            funcionarioService.eliminarSoloRol(idPerfil);
        } else if (generalService.obtenerPorId(idPerfil).isPresent()) {
            generalService.eliminarSoloRol(idPerfil);
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
                general.setEstadoVerificacionId("pendiente");
                general.setFecha_registro(LocalDate.now());
                generalService.crear(general);
            }
            default -> {
                return ResponseEntity.badRequest().body("Rol inválido");
            }
        }

        return ResponseEntity.ok("Rol cambiado correctamente");
    }

    //Endpoint de prueba para crear un admin temporal, con mail, contraseña y sede fijo.
    //El endpoint es POST /administradores/crear-admin-temporal, y no recibe ningún parámetro, simplemente crea un admin con los datos predefinidos.
    @Transactional
    @PostMapping("/crear-admin-temporal")
    public ResponseEntity<?> crearAdminTemporal() {
        String mail = "admin.test@mundial2026.com";
        String password = "admin1234";
        String paisSede = "México";

        if (usuarioService.obtenerPorMail(mail).isPresent()) {
            return ResponseEntity.badRequest().body("El administrador temporal ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setMail(mail);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setDireccionPais(paisSede);
        usuario.setDocumentoTipo("N/A");
        usuario.setDocumentoNumeroDoc("N/A");
        usuario.setDireccionCalle("N/A");
        usuario.setDireccionNumero("N/A");
        usuario.setDireccionCodigoPostal("N/A");
        usuario.setDireccionLocalidad("N/A");
        usuarioService.crear(usuario);

        Perfil perfil = new Perfil();
        perfil.setUsuario(usuario);
        perfil = perfilService.crear(perfil);

        Administrador administrador = new Administrador();
        administrador.setPerfil(perfil);
        administrador.setFecha_asignado(LocalDate.now());
        administrador.setPaisSede(paisSede);

        return ResponseEntity.ok(administradorService.crear(administrador));
    }

    @GetMapping("/verificaciones/pendientes")
    public ResponseEntity<?> obtenerPendientes() {

        List<General> pendientes = generalRepository.findByEstadoVerificacionId("pendiente");

        return ResponseEntity.ok(pendientes);
    }

    @Transactional
    @PostMapping("/verificaciones/responder")
    public ResponseEntity<?> responderVerificacion(@RequestBody Map<String, Object> datos) {

        Integer idGeneral = (Integer) datos.get("idGeneral");
        String estado = (String) datos.get("estado");

        if (!estado.equals("verificado") &&
            !estado.equals("rechazado")) {

            return ResponseEntity.badRequest().body("Estado inválido");
        }

        Optional<General> generalOpt = generalRepository.findById(idGeneral);

        if (generalOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        General general = generalOpt.get();

        if (!general.getEstadoVerificacionId().equals("pendiente")) {
            return ResponseEntity.badRequest().body("La solicitud ya fue procesada");
        }

        general.setEstadoVerificacionId(estado);
        generalRepository.save(general);

        return ResponseEntity.ok(
            "Usuario " + estado + " correctamente"
        );
    }
}
