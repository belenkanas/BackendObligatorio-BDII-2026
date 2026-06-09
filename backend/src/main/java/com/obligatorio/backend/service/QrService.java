package com.obligatorio.backend.service;

import java.time.Instant;

public interface QrService {
    String generateTokenForEntrada(Integer entradaId, Instant now);
    boolean validateToken(String token, Integer entradaId);
}
