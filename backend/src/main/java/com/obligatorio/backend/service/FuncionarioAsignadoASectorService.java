package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.FuncionarioAsignadoASector;
import com.obligatorio.backend.model.FuncionarioAsignadoASectorId;
import com.obligatorio.backend.repository.FuncionarioAsignadoASectorRepository;

@Service
public class FuncionarioAsignadoASectorService {

    @Autowired
    private FuncionarioAsignadoASectorRepository funcionarioAsignadoASectorRepository;

    public List<FuncionarioAsignadoASector> obtenerTodos() {
        return funcionarioAsignadoASectorRepository.findAll();
    }

    public Optional<FuncionarioAsignadoASector> obtenerPorId(FuncionarioAsignadoASectorId id) {
        return funcionarioAsignadoASectorRepository.findById(id);
    }

    public FuncionarioAsignadoASector crear(FuncionarioAsignadoASector funcionarioAsignadoASector) {
        String nroLegajo = funcionarioAsignadoASector.getId().getNroLegajo();
        java.time.LocalDateTime fechaNueva = funcionarioAsignadoASector.getId().getFechaHoraPartido();
        String estadioNuevo = funcionarioAsignadoASector.getId().getEstadioNombre();

        List<FuncionarioAsignadoASector> asignacionesExistentes = funcionarioAsignadoASectorRepository.findByIdNroLegajo(nroLegajo);

        boolean conflicto = asignacionesExistentes.stream()
            .anyMatch(a -> {
                java.time.LocalDateTime fechaExistente = a.getId().getFechaHoraPartido();
                String estadioExistente = a.getId().getEstadioNombre();

                // Si es el mismo estadio y evento, no es conflicto (es el mismo evento, diferente sector)
                if (estadioExistente.equals(estadioNuevo) && fechaExistente.equals(fechaNueva)) {
                    return false;
                }

                // Verificar si hay solapamiento en rango de 2 horas
                long diferenciaMinutos = Math.abs(java.time.Duration.between(fechaExistente, fechaNueva).toMinutes());
                return diferenciaMinutos < 120;
            });

        if (conflicto) {
            throw new IllegalStateException("El funcionario ya tiene un evento asignado en un horario cercano (dentro de 2 horas). No se puede asignar.");
        }

        return funcionarioAsignadoASectorRepository.save(funcionarioAsignadoASector);
    }

    public void eliminar(FuncionarioAsignadoASectorId id) {
        funcionarioAsignadoASectorRepository.deleteById(id);
    }

    public List<FuncionarioAsignadoASector> obtenerPorLegajo(String legajo) {
        return funcionarioAsignadoASectorRepository.findByIdNroLegajo(legajo);
}
}