package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeccionCustomRequestDTO {
    private String titulo;
    private String dificultad;
    private Long creadorId;
    private Long estudianteId;
    private Long temaId;
}
