package com.obligatorio.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.model.EntradaTieneToken;
import com.obligatorio.backend.model.EntradaTieneTokenId;
import com.obligatorio.backend.model.Token;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.EntradaTieneTokenRepository;
import com.obligatorio.backend.repository.TokenRepository;
import com.obligatorio.backend.service.EntradaService;

@RestController
@RequestMapping("/entradas")
@CrossOrigin(origins = "*")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EntradaTieneTokenRepository entradaTieneTokenRepository;

    @GetMapping
    public List<Entrada> obtenerTodos() {
        return entradaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Entrada> obtenerPorId(@PathVariable Integer id) {
        return entradaService.obtenerPorId(id);
    }

    @PostMapping
    public Entrada crear(@RequestBody Entrada entrada) {
        return entradaService.crear(entrada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        entradaService.eliminar(id);
    }

    @GetMapping("/usuario/{idGeneral}")
    public List<Entrada> obtenerPorUsuario(@PathVariable Integer idGeneral) {
        return entradaService.obtenerPorPropietario(idGeneral);
    }

    @GetMapping("/venta/{idVenta}")
    public List<Entrada> obtenerPorVenta(@PathVariable Integer idVenta) {
        return entradaService.obtenerPorVenta(idVenta);
    }

    @Transactional
    @PostMapping("/{id}/generar-token")
    public ResponseEntity<?> generarToken(@PathVariable Integer id) {
        Optional<Entrada> entradaOpt = entradaRepository.findById(id);
        if (entradaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Entrada no encontrada");
        }

        Entrada entrada = entradaOpt.get();
        if (!entrada.getEstado().equals("activa")) {
            return ResponseEntity.badRequest().body("La entrada no está activa");
        }

        // invalidar token anterior si existe
        Optional<EntradaTieneToken> tokenAnteriorOpt = entradaTieneTokenRepository.findByIdIdEntrada(id);
        if (tokenAnteriorOpt.isPresent()) {
            Token tokenViejo = tokenRepository.findById(tokenAnteriorOpt.get().getId().getQr()).orElse(null);
            if (tokenViejo != null) {
                tokenViejo.setValido(false);
                tokenRepository.save(tokenViejo);
            }
            entradaTieneTokenRepository.deleteByIdIdEntrada(id);
        }

        // crear nuevo token
        String qr = UUID.randomUUID().toString();
        LocalDateTime caducidad = LocalDateTime.now().plusSeconds(30); // token valido por 30 segundos

        Token token = new Token();
        token.setQr(qr);
        token.setValido(true);
        tokenRepository.save(token);

        EntradaTieneToken ett = new EntradaTieneToken();
        EntradaTieneTokenId ettId = new EntradaTieneTokenId();
        ettId.setQr(qr);
        ettId.setIdEntrada(id);
        ett.setId(ettId);
        ett.setFechaCaducidad(caducidad);
        entradaTieneTokenRepository.save(ett);

        return ResponseEntity.ok(Map.of("qr", qr, "caducidad", caducidad.toString()));
    }
}