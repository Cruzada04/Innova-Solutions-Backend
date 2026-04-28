package com.upc.innovasolutionsbackend.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgresoEvaluacionRequestDTO {
    private Integer puntaje;
    private Integer medallasObtenidas;
    private LocalDateTime fechaEvaluacion;
    private String reporteGenerado;
    private Long estudianteId;
    private Long leccionId;
}
