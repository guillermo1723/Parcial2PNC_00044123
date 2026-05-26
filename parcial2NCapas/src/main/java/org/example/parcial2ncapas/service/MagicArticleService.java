package org.example.parcial2ncapas.service;

import org.example.parcial2ncapas.dto.article.*;

import java.util.List;

public interface MagicArticleService {

    MagicArticleResponseDTO createArticle(
            MagicArticleRequestDTO requestDTO
    );

    List<MagicArticleResponseDTO> getAllArticles();

    MagicArticleResponseDTO getArticleById(Long id);

    MagicArticleResponseDTO updateArticle(
            Long id,
            MagicArticleRequestDTO requestDTO
    );

    void deleteArticle(Long id);
}