package org.example.parcial2ncapas.dto;

import lombok.*;
import org.example.parcial2ncapas.entity.Type;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicProviderResponseDTO {

        private Long id;

        private String name;

        private Type type;

}
