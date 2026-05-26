package org.example.parcial2ncapas.service;

import org.example.parcial2ncapas.dto.MagicArticleRequestDTO;
import org.example.parcial2ncapas.dto.MagicArticleResponseDTO;
import org.example.parcial2ncapas.entity.MagicArticle;
import org.example.parcial2ncapas.entity.MagicProvider;
import org.example.parcial2ncapas.exception.BusinessRuleException;
import org.example.parcial2ncapas.exception.DuplicateResourceException;
import org.example.parcial2ncapas.exception.ResourceNotFoundException;
import org.example.parcial2ncapas.repository.MagicArticleRepository;
import org.example.parcial2ncapas.repository.MagicProviderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MagicArticleServiceImpl implements MagicArticleService {

    private final MagicArticleRepository articleRepository;

    private final MagicProviderRepository providerRepository;

    @Override
    public MagicArticleResponseDTO createArticle(
            MagicArticleRequestDTO requestDTO
    ) {

        if (articleRepository.existsByNameIgnoreCase(
                requestDTO.getName()
        )) {

            throw new DuplicateResourceException(
                    "Ya existe un artículo con ese nombre"
            );
        }

        MagicProvider provider =
                providerRepository.findById(
                        requestDTO.getProviderId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException("Proveedor no encontrado"));

        // VALIDAR TIPO
        if (provider.getType() != requestDTO.getType()) {

            throw new BusinessRuleException(
                    "El tipo del artículo no coincide con el proveedor"
            );
        }

        MagicArticle article = MagicArticle.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .price(requestDTO.getPrice())
                .provider(provider)
                .build();

        MagicArticle savedArticle =
                articleRepository.save(article);

        return mapToResponse(savedArticle);
    }

    @Override
    public List<MagicArticleResponseDTO> getAllArticles() {

        return articleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MagicArticleResponseDTO getArticleById(Long id) {

        MagicArticle article = articleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Artículo no encontrado"));

        return mapToResponse(article);
    }

    @Override
    public MagicArticleResponseDTO updateArticle(
            Long id,
            MagicArticleRequestDTO requestDTO
    ) {

        MagicArticle article = articleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Artículo no encontrado"));

        if (!article.getName().equalsIgnoreCase(
                requestDTO.getName())
                &&
                articleRepository.existsByNameIgnoreCase(
                        requestDTO.getName())
        ) {

            throw new DuplicateResourceException("Ya existe un artículo con ese nombre");
        }

        MagicProvider provider =
                providerRepository.findById(
                        requestDTO.getProviderId()).orElseThrow(() ->
                        new ResourceNotFoundException("Proveedor no encontrado"));

        // se valida el tipo
        if (provider.getType() != requestDTO.getType()) {

            throw new BusinessRuleException(
                    "El tipo del artículo no coincide con el proveedor"
            );
        }

        article.setName(requestDTO.getName());
        article.setType(requestDTO.getType());
        article.setPrice(requestDTO.getPrice());
        article.setProvider(provider);

        MagicArticle updatedArticle =
                articleRepository.save(article);

        return mapToResponse(updatedArticle);
    }

    @Override
    public void deleteArticle(Long id) {

        MagicArticle article = articleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Artículo no encontrado"
                        ));

        articleRepository.delete(article);
    }




    private MagicArticleResponseDTO mapToResponse(
            MagicArticle article
    ) {

        return MagicArticleResponseDTO.builder()
                .id(article.getId())
                .name(article.getName())
                .type(article.getType())
                .price(article.getPrice())
                .providerName(article.getProvider().getName())
                .build();
    }
}