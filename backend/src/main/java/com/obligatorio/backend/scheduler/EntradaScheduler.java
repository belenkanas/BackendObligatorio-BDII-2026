package com.obligatorio.backend.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.model.TransfEntrada;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.TransfEntradaRepository;

@Component
public class EntradaScheduler {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private TransfEntradaRepository transfEntradaRepository;

    // corre cada hora
    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void actualizarEntradasVencidas() {
        LocalDateTime ahora = LocalDateTime.now();

        // marcar como no_consumida las entradas activas de eventos pasados
        List<Entrada> entradasActivas = entradaRepository.findByEstado("activa");
        for (Entrada entrada : entradasActivas) {
            if (entrada.getFechaHoraPartido().isBefore(ahora)) {
                entrada.setEstado("no_consumida");
                entradaRepository.save(entrada);
            }
        }

        // cancelar transferencias pendientes de eventos pasados
        List<TransfEntrada> pendientes = transfEntradaRepository.findByEstado("pendiente");
        for (TransfEntrada transf : pendientes) {
            Entrada entrada = entradaRepository.findById(transf.getId().getIdEntrada()).orElse(null);
            if (entrada != null && entrada.getFechaHoraPartido().isBefore(ahora)) {
                transf.setEstado("cancelada");
                transfEntradaRepository.save(transf);
                entrada.setEstado("no_consumida");
                entradaRepository.save(entrada);
            }
        }
    }
}