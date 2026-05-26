package org.example.parcial2ncapas.service;

import org.example.parcial2ncapas.dto.provider.*;
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
public class MagicProviderServiceImpl
        implements MagicProviderService {

    private final MagicProviderRepository providerRepository;

    private final MagicArticleRepository articleRepository;

    @Override
    public MagicProviderResponseDTO createProvider(
            MagicProviderRequestDTO requestDTO
    ) {

        if (providerRepository.existsByNameIgnoreCase(
                requestDTO.getName()
        )) {

            throw new DuplicateResourceException(
                    "Ya existe un proveedor con ese nombre"
            );
        }

        MagicProvider provider = MagicProvider.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .build();

        MagicProvider savedProvider =
                providerRepository.save(provider);

        return mapToResponse(savedProvider);
    }

    @Override
    public List<MagicProviderResponseDTO> getAllProviders() {

        return providerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MagicProviderResponseDTO getProviderById(Long id) {

        MagicProvider provider = providerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Proveedor no encontrado"
                        ));

        return mapToResponse(provider);
    }

    @Override
    public MagicProviderResponseDTO updateProvider(
            Long id,
            MagicProviderRequestDTO requestDTO
    ) {

        MagicProvider provider = providerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Proveedor no encontrado"
                        ));

        if (!provider.getName().equalsIgnoreCase(
                requestDTO.getName())
                &&
                providerRepository.existsByNameIgnoreCase(
                        requestDTO.getName())
        ) {

            throw new DuplicateResourceException(
                    "Ya existe un proveedor con ese nombre"
            );
        }

        provider.setName(requestDTO.getName());
        provider.setType(requestDTO.getType());

        MagicProvider updatedProvider =
                providerRepository.save(provider);

        return mapToResponse(updatedProvider);
    }

    @Override
    public void deleteProvider(Long id) {

        MagicProvider provider = providerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Proveedor no encontrado"
                        ));

        long articlesCount =
                articleRepository.countByProviderId(id);

        if (articlesCount > 0) {

            throw new BusinessRuleException(
                    "No se puede eliminar un proveedor con artículos asociados"
            );
        }

        providerRepository.delete(provider);
    }

    private MagicProviderResponseDTO mapToResponse(
            MagicProvider provider
    ) {

        return MagicProviderResponseDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .type(provider.getType())
                .build();
    }
}