package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementoGuardado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoElemento;
    private Long elementoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
