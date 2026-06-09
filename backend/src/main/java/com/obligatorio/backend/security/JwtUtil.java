package com.obligatorio.backend.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public String generateToken(String subject) {
      
        return "token-for-" + subject;
    }

    public boolean validateToken(String token) {
    
        return token != null && token.startsWith("token-for-");
    }
}
