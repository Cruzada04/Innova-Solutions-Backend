package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelacionTutorEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Usuario tutor;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;
}
