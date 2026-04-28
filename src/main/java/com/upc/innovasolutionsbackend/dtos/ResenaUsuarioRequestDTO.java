package com.upc.innovasolutionsbackend.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResenaUsuarioRequestDTO {
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaPublicacion;
    private Long usuarioId;
}
