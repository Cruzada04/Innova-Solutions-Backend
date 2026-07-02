package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String preguntaTexto;
    private String imagenUrl;
    private String colorFondo;
    private String colorTexto;

    @ManyToOne
    @JoinColumn(name = "leccion_id")
    private LeccionCustom leccion;

    @OneToMany(mappedBy = "flashcard", cascade = CascadeType.ALL)
    private List<OpcionRespuesta> opciones;
}
