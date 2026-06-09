package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.Venta;
import com.obligatorio.backend.repository.VentaRepository;

@Service
public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> obtenerTodos() { 
        return ventaRepository.findAll(); 
    }

    public Optional<Venta> obtenerPorId(Integer id) { 
        return ventaRepository.findById(id); 
    }
    
    public List<Venta> obtenerPorGeneral(Integer idGeneral) { 
        return ventaRepository.findByGeneralIdGeneral(idGeneral); 
    }
    
    public Venta crear(Venta venta) { 
        return ventaRepository.save(venta); 
    }
    
    public void eliminar(Integer id) { 
        ventaRepository.deleteById(id); 
    }
}