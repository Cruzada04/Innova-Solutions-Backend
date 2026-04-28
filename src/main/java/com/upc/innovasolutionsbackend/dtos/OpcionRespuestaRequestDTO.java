package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcionRespuestaRequestDTO {
    private String textoOpcion;
    private Boolean esCorrecta;
    private String feedbackRespuesta;
    private Long flashcardId;
}
