package org.example.ejercicio2parcial.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Pattern(
            regexp = ".*[a-zA-Z].*",
            message = "El título no puede contener solo números"
    )
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "El autor es obligatorio")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "El ISBN es obligatorio")
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "El año de publicación es obligatorio")
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2026, message = "El año no puede ser mayor al año actual")
    @Column(nullable = false)
    private Integer publicationYear;

    private String language;

    @NotNull(message = "La cantidad de páginas es obligatoria")
    @Min(value = 11, message = "El número de páginas debe ser mayor a 10")
    @Column(nullable = false)
    private Integer pages;
}