package org.example.parcial2ncapas.repository;

import org.example.parcial2ncapas.entity.MagicArticle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MagicArticleRepository
        extends JpaRepository<MagicArticle, Long> {

    boolean existsByNameIgnoreCase(String name);

    long countByProviderId(Long providerId);
}

