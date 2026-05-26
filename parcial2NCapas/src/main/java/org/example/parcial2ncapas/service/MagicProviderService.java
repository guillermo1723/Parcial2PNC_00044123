package org.example.parcial2ncapas.service;

import org.example.parcial2ncapas.dto.provider.MagicProviderRequestDTO;
import org.example.parcial2ncapas.dto.provider.MagicProviderResponseDTO;

import java.util.List;

public interface MagicProviderService {

    MagicProviderResponseDTO createProvider(
            MagicProviderRequestDTO requestDTO
    );

    List<MagicProviderResponseDTO> getAllProviders();

    MagicProviderResponseDTO getProviderById(Long id);

    MagicProviderResponseDTO updateProvider(
            Long id,
            MagicProviderRequestDTO requestDTO
    );

    void deleteProvider(Long id);
}