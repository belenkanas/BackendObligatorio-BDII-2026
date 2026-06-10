package com.obligatorio.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class RootController {

    @GetMapping
    public ResponseEntity<Map<String, String>> raiz() {
        return ResponseEntity.ok(Map.of(
            "status", "ok",
            "app", "Mundial 2026 - Ticketing API",
            "version", "1.0.0"
        ));
    }
}