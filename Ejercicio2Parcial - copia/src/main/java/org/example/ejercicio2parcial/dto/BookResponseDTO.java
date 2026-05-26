package org.example.ejercicio2parcial.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private Integer publicationYear;

    private String language;

    private Integer pages;
}