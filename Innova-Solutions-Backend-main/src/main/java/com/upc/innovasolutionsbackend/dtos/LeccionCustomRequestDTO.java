package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeccionCustomRequestDTO {

    @NotBlank(message = "El título de la lección es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "La dificultad es obligatoria")
    private String dificultad;

    @NotNull(message = "El ID del creador es obligatorio")
    private Long creadorId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    @NotNull(message = "El ID del tema es obligatorio")
    private Long temaId;
}
