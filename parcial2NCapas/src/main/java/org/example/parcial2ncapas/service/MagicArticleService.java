package org.example.parcial2ncapas.service;

import org.example.parcial2ncapas.dto.MagicArticleRequestDTO;
import org.example.parcial2ncapas.dto.MagicArticleResponseDTO;

public interface MagicArticleService {
    MagicArticleResponseDTO createArticle(
            MagicArticleRequestDTO requestDTO
    );
}
