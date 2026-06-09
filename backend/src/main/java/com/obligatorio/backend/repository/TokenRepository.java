package com.obligatorio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obligatorio.backend.model.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
}