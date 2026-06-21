package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.DispositivoEscaneo;
import com.obligatorio.backend.model.Validacion;
import com.obligatorio.backend.model.ValidacionId;
import com.obligatorio.backend.repository.DispositivoEscaneoRepository;
import com.obligatorio.backend.repository.FuncionarioRepository;
import com.obligatorio.backend.repository.ValidacionRepository;

import jakarta.transaction.Transactional;

@Service
public class DispositivoEscaneoService {

    @Autowired
    private DispositivoEscaneoRepository dispositivoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private ValidacionRepository validacionRepository;

    public List<DispositivoEscaneo> obtenerTodos() {
        return dispositivoRepository.findAll();
    }

    public Optional<DispositivoEscaneo> obtenerPorId(Integer id) {
        return dispositivoRepository.findById(id);
    }

    public List<DispositivoEscaneo> obtenerPorFuncionario(String nroLegajo) {
        return dispositivoRepository.findByNroLegajo(nroLegajo);
    }

    public boolean existePorNroSerie(String nroSerie) {
        return dispositivoRepository.existsByNroSerie(nroSerie);
    }

    public DispositivoEscaneo crear(DispositivoEscaneo disp) {
        return dispositivoRepository.save(disp);
    }

    @Transactional
    public DispositivoEscaneo asignar(Integer id, String nroLegajo) {
        DispositivoEscaneo disp = dispositivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado"));

        if (disp.getNroLegajo() != null) {
            throw new RuntimeException("El dispositivo ya está asignado a un funcionario");
        }

        if (!funcionarioRepository.existsByNroLegajo(nroLegajo)) {
            throw new RuntimeException("El funcionario no existe");
        }

        // Actualizar nroLegajo en dispositivo_escaneo
        disp.setNroLegajo(nroLegajo);
        dispositivoRepository.save(disp);

        // Insertar en validacion
        ValidacionId validacionId = new ValidacionId();
        validacionId.setNroLegajoFuncionario(nroLegajo);
        validacionId.setIdDispositivoEscaneo(id);

        Validacion validacion = new Validacion();
        validacion.setId(validacionId);
        validacionRepository.save(validacion);

        return disp;
    }

    public DispositivoEscaneo desasignar(Integer id) {
        DispositivoEscaneo disp = dispositivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado"));

        if (disp.getNroLegajo() == null) {
            throw new RuntimeException("El dispositivo no está asignado a ningún funcionario");
        }

        // Borrar la validacion asociada
        validacionRepository.findAll().stream()
            .filter(v -> v.getId().getIdDispositivoEscaneo().equals(id))
            .forEach(v -> validacionRepository.delete(v));

        disp.setNroLegajo(null);
        return dispositivoRepository.save(disp);
    }

    public void eliminar(Integer id) {
        dispositivoRepository.deleteById(id);
    }
}