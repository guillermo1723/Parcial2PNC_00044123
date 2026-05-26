package org.example.parcial2ncapas.entity;

import org.example.parcial2ncapas.enums.MagicType;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "magic_providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MagicType type;

    @OneToMany(mappedBy = "provider")
    private List<MagicArticle> articles = new ArrayList<>();
}