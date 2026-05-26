package org.example.parcial2ncapas.dto;

import lombok.*;

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
