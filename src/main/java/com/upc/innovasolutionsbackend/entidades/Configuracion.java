package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer velocidadFlashcards;
    private String temaVisual;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
