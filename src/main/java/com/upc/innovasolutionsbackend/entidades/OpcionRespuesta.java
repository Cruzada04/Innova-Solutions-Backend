package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcionRespuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textoOpcion;
    private Boolean esCorrecta;
    private String feedbackRespuesta;

    @ManyToOne
    @JoinColumn(name = "flashcard_id")
    private Flashcard flashcard;
}
