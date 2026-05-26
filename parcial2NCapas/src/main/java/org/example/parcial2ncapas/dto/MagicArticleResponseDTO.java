package org.example.parcial2ncapas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MagicArticleResponseDTO {

        private Long id;

        private String name;

        private Enum type;

        private double price;

        private Integer publicationYear;

}
