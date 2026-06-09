package com.obligatorio.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obligatorio.backend.model.EntradaTieneToken;
import com.obligatorio.backend.model.EntradaTieneTokenId;
import com.obligatorio.backend.repository.EntradaTieneTokenRepository;

@Service
public class EntradaTieneTokenService {
    
    @Autowired
    private EntradaTieneTokenRepository entradaTokenRepository;

    public List<EntradaTieneToken> obtenerTodos() { return entradaTokenRepository.findAll(); }
    
    public Optional<EntradaTieneToken> obtenerPorId(EntradaTieneTokenId id) { return entradaTokenRepository.findById(id); }
    
    public EntradaTieneToken crear(EntradaTieneToken entrada) { return entradaTokenRepository.save(entrada); }
    
    public void eliminar(EntradaTieneTokenId id) { entradaTokenRepository.deleteById(id); }
}