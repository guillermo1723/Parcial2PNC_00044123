package org.example.parcial2ncapas.dto;

import jakarta.validation.constraints.*;
import lombok.*;

public class MagicArticleRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(
            regexp = ".*[a-zA-Z].*",
            message = "El nombre no puede contener solo números"
    )
    private String name;

    @NotBlank(message = "El autor es obligatorio")
    private String type;

    @NotBlank(message = "El precio es obligatorio")
    private float price;

    private String provider;


}


