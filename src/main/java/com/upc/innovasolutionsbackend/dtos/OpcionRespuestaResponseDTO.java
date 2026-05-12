package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcionRespuestaResponseDTO {
    private Long id;
    private String textoOpcion;
    private Boolean esCorrecta;
    private String feedbackRespuesta;
    private FlashcardResponseDTO flashcard;
}
