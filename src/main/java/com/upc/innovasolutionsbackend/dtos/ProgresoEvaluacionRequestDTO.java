package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgresoEvaluacionRequestDTO {

    @NotNull(message = "El puntaje es obligatorio")
    @Min(value = 0, message = "El puntaje no puede ser negativo")
    private Integer puntaje;

    @NotNull(message = "La cantidad de medallas es obligatoria")
    @Min(value = 0, message = "Las medallas no pueden ser negativas")
    private Integer medallasObtenidas;

    @NotNull(message = "La fecha de evaluación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime fechaEvaluacion;

    @NotBlank(message = "El reporte generado es obligatorio")
    private String reporteGenerado;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    @NotNull(message = "El ID de la lección es obligatorio")
    private Long leccionId;
}
