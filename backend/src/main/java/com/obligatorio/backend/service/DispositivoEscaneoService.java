package com.obligatorio.backend.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.DispositivoEscaneo;
import com.obligatorio.backend.repository.DispositivoEscaneoRepository;

@Service
public class DispositivoEscaneoService {

    @Autowired
    private DispositivoEscaneoRepository dispositivoRepository;

    public List<DispositivoEscaneo> obtenerTodos() { 
        return dispositivoRepository.findAll(); 
    }

    public Optional<DispositivoEscaneo> obtenerPorId(Integer id) { 
        return dispositivoRepository.findById(id); 
    }

    public DispositivoEscaneo crear(DispositivoEscaneo disp) { 
        return dispositivoRepository.save(disp); 
    }

    public void eliminar(Integer id) { 
        dispositivoRepository.deleteById(id); 
    }
}
