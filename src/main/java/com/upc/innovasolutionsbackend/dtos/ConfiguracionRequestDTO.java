package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionRequestDTO {

    @NotNull(message = "La velocidad de flashcards es obligatoria")
    @Min(value = 1, message = "La velocidad mínima debe ser 1")
    @Max(value = 10, message = "La velocidad máxima permitida es 10")
    private Integer velocidadFlashcards;

    @NotBlank(message = "El tema visual es obligatorio")
    private String temaVisual;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;
}
