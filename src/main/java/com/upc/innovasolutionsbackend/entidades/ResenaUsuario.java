package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResenaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
