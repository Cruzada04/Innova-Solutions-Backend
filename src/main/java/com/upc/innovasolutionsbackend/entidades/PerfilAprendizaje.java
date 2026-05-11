package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilAprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nivelEspectro;
    private String necesidadesEspecificas;

    @OneToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;
}
