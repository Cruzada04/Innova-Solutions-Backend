package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardResponseDTO {
    private Long id;
    private String preguntaTexto;
    private String imagenUrl;
    private String colorFondo;
    private String colorTexto;
    private LeccionCustomResponseDTO leccion;
}
