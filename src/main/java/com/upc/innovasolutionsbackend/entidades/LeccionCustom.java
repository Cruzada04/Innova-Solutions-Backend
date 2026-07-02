package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeccionCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String dificultad;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Usuario creador;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "tema_id")
    private Tema tema;

    @OneToMany(mappedBy = "leccion")
    private List<ProgresoEvaluacion> progresos;

    @OneToMany(mappedBy = "leccion")
    private List<Flashcard> flashcards;
}
