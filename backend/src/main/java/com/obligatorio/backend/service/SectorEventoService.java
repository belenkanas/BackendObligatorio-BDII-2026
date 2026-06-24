package com.obligatorio.backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Administrador;
import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;
import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;
import com.obligatorio.backend.repository.AdministradorRepository;
import com.obligatorio.backend.repository.EventoRepository;
import com.obligatorio.backend.repository.SectorEventoRepository;

@Service
public class SectorEventoService {

    @Autowired
    private SectorEventoRepository sectorEventoRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public List<SectorEvento> obtenerTodos() {
        return sectorEventoRepository.findAll();
    }

    public Optional<SectorEvento> obtenerPorId(SectorEventoId id) {
        return sectorEventoRepository.findById(id);
    }

    public List<SectorEvento> obtenerPorEvento(String estadio, String pais, String ciudad, LocalDateTime fecha) {
        return sectorEventoRepository.findByEvento(estadio, pais, ciudad, fecha);
    }

    private void validarPaisSede(String estadioDireccionPais, Integer idAdministrador) {
        Administrador admin = administradorRepository.findById(idAdministrador)
            .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado"));
        if (admin.getPaisSede() == null || !admin.getPaisSede().equals(estadioDireccionPais)) {
            throw new IllegalArgumentException(
                "No tenés permiso para gestionar sectores de estadios fuera de tu país sede"
            );
        }
    }

    public SectorEvento crear(SectorEvento sectorEvento, Integer idAdministrador) {
        validarPaisSede(sectorEvento.getId().getEstadioDireccionPais(), idAdministrador);
        return sectorEventoRepository.save(sectorEvento);
    }

    public void eliminar(SectorEventoId id, Integer idAdministrador) {
        validarPaisSede(id.getEstadioDireccionPais(), idAdministrador);
        sectorEventoRepository.deleteById(id);
    }

    public void eliminar(SectorEventoId id) {
        sectorEventoRepository.deleteById(id);
    }

    public Optional<SectorEvento> actualizarCosto(SectorEventoId id, BigDecimal nuevoCosto, Integer idAdministrador) {
        validarPaisSede(id.getEstadioDireccionPais(), idAdministrador);
        validarEventoActivo(id);

        return sectorEventoRepository.findById(id).map(se -> {
            se.setCosto(nuevoCosto);
            return sectorEventoRepository.save(se);
        });
    }

    private void validarEventoActivo(SectorEventoId id) {
        EventoId eventoId = new EventoId();
        eventoId.setEstadioNombre(id.getEstadioNombre());
        eventoId.setEstadioDireccionPais(id.getEstadioDireccionPais());
        eventoId.setEstadioDireccionCiudad(id.getEstadioDireccionCiudad());
        eventoId.setFechaHoraPartido(id.getFechaHoraPartido());
        // Los campos de equipos no son parte de SectorEventoId, 
        // así que buscamos por estadio + fecha directamente
        Evento evento = eventoRepository
            .findByEstadioYFecha(
                id.getEstadioNombre(),
                id.getEstadioDireccionPais(),
                id.getEstadioDireccionCiudad(),
                id.getFechaHoraPartido()
            )
            .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        if (id.getFechaHoraPartido().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se puede modificar el costo de un sector de un evento ya disputado");
        }
        if ("suspendido".equalsIgnoreCase(evento.getEstado())) {
            throw new IllegalArgumentException("No se puede modificar el costo de un sector de un evento suspendido");
        }
    }
}