package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardRequestDTO {

    @NotBlank(message = "El texto de la pregunta es obligatorio")
    @Size(max = 500, message = "La pregunta no puede exceder los 500 caracteres")
    private String preguntaTexto;

    private String imagenUrl; // Opcional, no requiere validación de obligatoriedad

    @NotBlank(message = "El color de fondo es obligatorio")
    private String colorFondo;

    @NotBlank(message = "El color de texto es obligatorio")
    private String colorTexto;

    @NotNull(message = "El ID de la lección es obligatorio")
    private Long leccionId;
}
