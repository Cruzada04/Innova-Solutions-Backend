package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcionRespuestaItemDTO {

    @NotBlank(message = "El texto de la opción es obligatorio")
    @Size(max = 255, message = "El texto no puede exceder los 255 caracteres")
    private String textoOpcion;

    @NotNull(message = "Debe indicar si la opción es correcta")
    private Boolean esCorrecta;

    @NotBlank(message = "El feedback de la respuesta es obligatorio")
    private String feedbackRespuesta;

    //Sin flashcardId, porque el padre (Flashcard) lo asigna en el service
}
