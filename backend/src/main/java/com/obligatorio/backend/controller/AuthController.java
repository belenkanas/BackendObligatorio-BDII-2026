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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.General;
import com.obligatorio.backend.model.Perfil;
import com.obligatorio.backend.model.TelefonosUsuario;
import com.obligatorio.backend.model.TelefonosUsuarioId;
import com.obligatorio.backend.model.Usuario;
import com.obligatorio.backend.service.AdministradorService;
import com.obligatorio.backend.service.FuncionarioService;
import com.obligatorio.backend.service.GeneralService;
import com.obligatorio.backend.service.PerfilService;
import com.obligatorio.backend.service.TelefonosUsuarioService;
import com.obligatorio.backend.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private GeneralService generalService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TelefonosUsuarioService telefonoUsuarioService;

    @Transactional
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Map<String, Object> datos) {

        String mail = (String) datos.get("mail");
        if (usuarioService.obtenerPorMail(mail).isPresent()) {
            return ResponseEntity.badRequest().body("El mail ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setMail(mail);
        usuario.setPassword(passwordEncoder.encode((String) datos.get("password")));
        usuario.setDocumentoTipo((String) datos.get("documentoTipo"));
        usuario.setDocumentoNumeroDoc((String) datos.get("documentoNumeroDoc"));
        usuario.setDireccionCalle((String) datos.get("direccionCalle"));
        usuario.setDireccionNumero((String) datos.get("direccionNumero"));
        usuario.setDireccionCodigoPostal((String) datos.get("direccionCodigoPostal"));
        usuario.setDireccionPais((String) datos.get("direccionPais"));
        usuario.setDireccionLocalidad((String) datos.get("direccionLocalidad"));
        usuarioService.crear(usuario);

        List<String> telefonos = (List<String>) datos.get("telefonos");
        if (telefonos != null && !telefonos.isEmpty()) {
            for (String telefono : telefonos) {
                TelefonosUsuario tel = new TelefonosUsuario();
                TelefonosUsuarioId telId = new TelefonosUsuarioId();
                telId.setMailUsuario(mail);
                telId.setTelefono(telefono);
                tel.setId(telId);
                telefonoUsuarioService.crear(tel);
            }
        }

        Perfil perfil = new Perfil();
        perfil.setUsuario(usuario);
        perfil = perfilService.crear(perfil);

        General general = new General();
        general.setPerfil(perfil);
        general.setEstado_verificacion_id("pendiente");
        general.setFecha_registro(LocalDate.now());
        generalService.crear(general);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {

        String mail = datos.get("mail");
        String password = datos.get("password");

        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorMail(mail);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Mail o contraseña incorrectos");
        }

        Usuario usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            return ResponseEntity.status(401).body("Mail o contraseña incorrectos");
        }

        List<Perfil> perfiles = perfilService.obtenerPorUsuario(mail);
        if (perfiles.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario sin perfil asignado");
        }

        Perfil perfil = perfiles.get(0);
        Integer idPerfil = perfil.getId();

        String rol = "GENERAL";
        if (administradorService.obtenerPorId(idPerfil).isPresent()) {
            rol = "ADMINISTRADOR";
        } else if (funcionarioService.obtenerPorId(idPerfil).isPresent()) {
            rol = "FUNCIONARIO";
        }

        return ResponseEntity.ok(Map.of(
            "mail", usuario.getMail(),
            "idPerfil", idPerfil,
            "rol", rol,
            "mensaje", "Login exitoso"
        ));
    }
}