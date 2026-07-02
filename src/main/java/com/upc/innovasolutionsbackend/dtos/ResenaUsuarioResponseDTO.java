package com.upc.innovasolutionsbackend.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResenaUsuarioResponseDTO {
    private Long id;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaPublicacion;
    private UsuarioResponseDTO usuario;
}
