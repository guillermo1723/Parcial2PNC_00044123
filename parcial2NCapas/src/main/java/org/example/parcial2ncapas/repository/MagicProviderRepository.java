package org.example.parcial2ncapas.repository;

import org.example.parcial2ncapas.entity.MagicProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MagicProviderRepository
        extends JpaRepository<MagicProvider, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<MagicProvider> findByNameIgnoreCase(String name);
}