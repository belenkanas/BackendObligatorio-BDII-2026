package com.obligatorio.backend.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.model.EntradaTieneToken;
import com.obligatorio.backend.model.Funcionario;
import com.obligatorio.backend.model.Token;
import com.obligatorio.backend.model.TokenEscaneadoValido;
import com.obligatorio.backend.model.TokenEscaneadoValidoId;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.EntradaTieneTokenRepository;
import com.obligatorio.backend.repository.FuncionarioAsignadoASectorRepository;
import com.obligatorio.backend.repository.TokenEscaneadoValidoRepository;
import com.obligatorio.backend.repository.TokenRepository;
import com.obligatorio.backend.service.FuncionarioService;

@RestController
@RequestMapping("/validacion")
@CrossOrigin(origins = "*")
public class ValidacionEscaneoController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EntradaTieneTokenRepository entradaTieneTokenRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private TokenEscaneadoValidoRepository tokenEscaneadoValidoRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioAsignadoASectorRepository funcionarioAsignadoASectorRepository;

    @Transactional
    @PostMapping("/escanear")
    public ResponseEntity<?> escanear(@RequestBody Map<String, Object> datos) {
        String qr = (String) datos.get("qr");
        Integer idFuncionario = (Integer) datos.get("idFuncionario");
        Integer idDispositivoEscaneo = (Integer) datos.get("idDispositivoEscaneo");

        if (qr == null || idFuncionario == null || idDispositivoEscaneo == null) {
            return ResponseEntity.badRequest().body("Faltan datos para validar el escaneo");
        }

        Optional<Token> tokenOpt = tokenRepository.findById(qr);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El código QR no existe");
        }

        Token token = tokenOpt.get();
        if (!Boolean.TRUE.equals(token.getValido())) {
            return ResponseEntity.badRequest().body("El código QR ya no es válido");
        }

        Optional<EntradaTieneToken> ettOpt = entradaTieneTokenRepository.findByIdQr(qr);
        if (ettOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El código QR no está asociado a ninguna entrada");
        }

        EntradaTieneToken ett = ettOpt.get();
        if (ett.getFechaCaducidad() != null && ett.getFechaCaducidad().isBefore(LocalDateTime.now())) {
            token.setValido(false);
            tokenRepository.save(token);
            return ResponseEntity.badRequest().body("El código QR caducó, generá uno nuevo");
        }

        Integer idEntrada = ett.getId().getIdEntrada();
        Optional<Entrada> entradaOpt = entradaRepository.findById(idEntrada);
        if (entradaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("La entrada asociada no existe");
        }

        Entrada entrada = entradaOpt.get();

        Optional<Funcionario> funcionarioOpt = funcionarioService.obtenerPorId(idFuncionario);
        if (funcionarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Funcionario no encontrado");
        }
        String nroLegajo = funcionarioOpt.get().getNroLegajo();
        
        boolean estaAsignado = funcionarioAsignadoASectorRepository.existeAsignacion(
            nroLegajo,
            entrada.getNombreSector(),
            entrada.getEstadioNombre(),
            entrada.getEstadioDireccionPais(),
            entrada.getEstadioDireccionCiudad(),
            entrada.getFechaHoraPartido()
        );

        if (!estaAsignado) {
            return ResponseEntity.badRequest().body(
                "No tenés permiso para validar entradas del sector " + entrada.getNombreSector()
            );
        }
        if (!"activa".equals(entrada.getEstado())) {
            return ResponseEntity.badRequest().body("La entrada no está activa (estado actual: " + entrada.getEstado() + ")");
        }

        token.setValido(false);
        tokenRepository.save(token);

        entrada.setEstado("consumida");
        entradaRepository.save(entrada);

        TokenEscaneadoValidoId escaneoId = new TokenEscaneadoValidoId();
        escaneoId.setNroLegajoFuncionario(nroLegajo);
        escaneoId.setIdDispositivoEscaneo(idDispositivoEscaneo);
        escaneoId.setFechaValidacion(LocalDateTime.now());
        escaneoId.setQrToken(qr);

        TokenEscaneadoValido escaneo = new TokenEscaneadoValido();
        escaneo.setId(escaneoId);
        tokenEscaneadoValidoRepository.save(escaneo);

        return ResponseEntity.ok(Map.of("mensaje", "Entrada validada correctamente. Ingreso autorizado."));
    }
}