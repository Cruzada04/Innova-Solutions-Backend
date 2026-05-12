package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardConOpcionesRequestDTO {

    @NotBlank(message = "El texto de la pregunta es obligatorio")
    @Size(max = 500, message = "La pregunta no puede exceder los 500 caracteres")
    private String preguntaTexto;

    private String imagenUrl;

    @NotBlank(message = "El color de fondo es obligatorio")
    private String colorFondo;

    @NotBlank(message = "El color de texto es obligatorio")
    private String colorTexto;

    @NotNull(message = "El ID de la lección es obligatorio")
    private Long leccionId;

    @NotNull
    @Size(min = 2, message = "Debe ingresar al menos 2 opciones de respuesta")
    private List<@Valid OpcionRespuestaItemDTO> opciones;
}
