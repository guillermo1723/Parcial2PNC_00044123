package org.example.parcial2ncapas.entity;

import org.example.parcial2ncapas.enums.MagicType;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "magic_articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MagicType type;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(
            value = "0.01",
            message = "El precio debe ser mayor a cero"
    )
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private MagicProvider provider;
}