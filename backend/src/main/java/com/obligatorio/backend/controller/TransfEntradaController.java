package com.obligatorio.backend.controller;

import java.time.LocalDateTime;
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

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.model.TransfEntrada;
import com.obligatorio.backend.model.TransfEntradaId;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.GeneralRepository;
import com.obligatorio.backend.service.TransfEntradaService;

@RestController
@RequestMapping("/transferencias-entrada")
@CrossOrigin(origins = "*")
public class TransfEntradaController {

    @Autowired
    private TransfEntradaService transfEntradaService;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private GeneralRepository generalRepository;


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

    // INICIAR TRANSFERENCIA
    @Transactional
    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarTransferencia(@RequestBody Map<String, Object> datos) {

        Integer idEntrada = (Integer) datos.get("idEntrada");
        Integer idGeneralRealiza = (Integer) datos.get("idGeneralRealiza");
        Integer idGeneralRecibe = (Integer) datos.get("idGeneralRecibe");

        // verificar que la entrada existe y pertenece al usuario
        Optional<Entrada> entradaOpt = entradaRepository.findById(idEntrada);
        if (entradaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Entrada no encontrada");
        }

        Entrada entrada = entradaOpt.get();

        // verificar que el usuario es el propietario
        if (!entrada.getIdGeneralPropietario().equals(idGeneralRealiza)) {
            return ResponseEntity.badRequest().body("No sos el propietario de esta entrada");
        }

        // maximo 3 transferencias por entrada
        if (entrada.getCantTransferida() >= 3) {
            return ResponseEntity.badRequest().body("Esta entrada ya alcanzó el límite de 3 transferencias");
        }

        // verificar que la entrada no está en un estado inválido
        if (!entrada.getEstado().equals("activo")) {
            return ResponseEntity.badRequest().body("La entrada no está disponible para transferir");
        }

        // verificar que el destinatario existe
        if (generalRepository.findById(idGeneralRecibe).isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario destinatario no existe");
        }

        // marcar entrada como en_transferencia
        entrada.setEstado("en_transferencia");
        entradaRepository.save(entrada);

        // crear transferencia
        TransfEntradaId transfId = new TransfEntradaId();
        transfId.setFechaHora(LocalDateTime.now());
        transfId.setIdEntrada(idEntrada);

        TransfEntrada transf = new TransfEntrada();
        transf.setId(transfId);
        transf.setEstado("pendiente");
        transf.setIdGeneralRealiza(idGeneralRealiza);
        transf.setIdGeneralRecibe(idGeneralRecibe);
        transfEntradaService.crear(transf);

        return ResponseEntity.ok("Se inició la transferencia correctamente");
    }

    // ACEPTAR O RECHAZAR TRANSFERENCIA
    @Transactional
    @PostMapping("/responder")
    public ResponseEntity<?> responderTransferencia(@RequestBody Map<String, Object> datos) {

        Integer idEntrada = (Integer) datos.get("idEntrada");
        String fechaHoraStr = (String) datos.get("fechaHora");
        String respuesta = (String) datos.get("respuesta"); // aceptar o rechazar

        TransfEntradaId transfId = new TransfEntradaId();
        transfId.setIdEntrada(idEntrada);
        transfId.setFechaHora(LocalDateTime.parse(fechaHoraStr));

        Optional<TransfEntrada> transfOpt = transfEntradaService.obtenerPorId(transfId);
        if (transfOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Transferencia no encontrada");
        }

        TransfEntrada transf = transfOpt.get();

        if (!transf.getEstado().equals("pendiente")) {
            return ResponseEntity.badRequest().body("La transferencia ya fue procesada");
        }

        Entrada entrada = entradaRepository.findById(idEntrada).get();

        if (respuesta.equals("aceptar")) {
            // la entrada original queda como transferida
            entrada.setEstado("transferido");
            entrada.setCantTransferida(entrada.getCantTransferida() + 1);
            transf.setEstado("aceptado");
            entradaRepository.save(entrada);
            transfEntradaService.crear(transf); 

            // y se crea una nueva entrada para el destinatario con la misma cantidad de transferencias
            Entrada nuevaEntrada = new Entrada();
            nuevaEntrada.setEstado("activo");
            nuevaEntrada.setCantTransferida(entrada.getCantTransferida());
            nuevaEntrada.setNombreSector(entrada.getNombreSector());
            nuevaEntrada.setEstadioNombre(entrada.getEstadioNombre());
            nuevaEntrada.setEstadioDireccionPais(entrada.getEstadioDireccionPais());
            nuevaEntrada.setEstadioDireccionCiudad(entrada.getEstadioDireccionCiudad());
            nuevaEntrada.setFechaHoraPartido(entrada.getFechaHoraPartido());
            nuevaEntrada.setNombrePaisEquipoLocal(entrada.getNombrePaisEquipoLocal());
            nuevaEntrada.setNombrePaisEquipoVisitante(entrada.getNombrePaisEquipoVisitante());
            nuevaEntrada.setIdGeneralPropietario(transf.getIdGeneralRecibe());
            nuevaEntrada.setIdVenta(entrada.getIdVenta());
            entradaRepository.save(nuevaEntrada);
        } else {
            entrada.setEstado("activo");
            transf.setEstado("rechazado");
            entradaRepository.save(entrada);
            transfEntradaService.crear(transf);
        }

        return ResponseEntity.ok("Transferencia " + respuesta + "da correctamente");
    }

    // transferencias pendientes que me enviaron
    @GetMapping("/pendientes/{idGeneral}")
    public List<TransfEntrada> obtenerPendientesRecibidas(@PathVariable Integer idGeneral) {
        return transfEntradaService.obtenerPendientesPorReceptor(idGeneral);
    }

    // historial completo del usuario
    @GetMapping("/historial/{idGeneral}")
    public List<TransfEntrada> obtenerHistorial(@PathVariable Integer idGeneral) {
        return transfEntradaService.obtenerHistorialPorUsuario(idGeneral);
    }
}