package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardRequestDTO {
    private String preguntaTexto;
    private String imagenUrl;
    private String audioUrl;
    private String colorFondo;
    private String colorTexto;
    private Boolean generadaPorIa;
    private Long leccionId;
}
