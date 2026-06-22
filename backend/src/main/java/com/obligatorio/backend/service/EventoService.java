package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obligatorio.backend.model.AdministradorGestionaEvento;
import com.obligatorio.backend.model.AdministradorGestionaEventoId;
import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.EventoId;
import com.obligatorio.backend.repository.AdministradorGestionaEventoRepository;
import com.obligatorio.backend.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AdministradorGestionaEventoRepository administradorGestionaEventoRepository;

    public List<Evento> obtenerTodos() { return eventoRepository.findAll(); }

    public Optional<Evento> obtenerPorId(EventoId id) { return eventoRepository.findById(id); }

    @Transactional
    public Evento crear(Evento evento, Integer idAdministrador) {
        Evento eventoGuardado = eventoRepository.save(evento);

        EventoId eid = eventoGuardado.getId();

        AdministradorGestionaEventoId relId = new AdministradorGestionaEventoId();
        relId.setIdAdministrador(idAdministrador);
        relId.setEstadioNombre(eid.getEstadioNombre());
        relId.setEstadioDireccionPais(eid.getEstadioDireccionPais());
        relId.setEstadioDireccionCiudad(eid.getEstadioDireccionCiudad());
        relId.setFechaHoraPartido(eid.getFechaHoraPartido());
        relId.setNombrePaisEquipoLocal(eid.getNombrePaisEquipoLocal());
        relId.setNombrePaisEquipoVisitante(eid.getNombrePaisEquipoVisitante());

        AdministradorGestionaEvento relacion = new AdministradorGestionaEvento();
        relacion.setId(relId);
        administradorGestionaEventoRepository.save(relacion);

        return eventoGuardado;
    }

    public void eliminar(EventoId id) { eventoRepository.deleteById(id); }

    // Actualizar el estado de un evento (activo/inactivo). 
    // Cuando se suspende un evento, en lugar de eliminarlo, se le cambia el estado para inhabilitar la venta de entradas para el mismo.
    public Optional<Evento> actualizarEstado(EventoId id, String nuevoEstado) {
    return eventoRepository.findById(id).map(e -> {
        e.setEstado(nuevoEstado);
        return eventoRepository.save(e);
    });
}
}