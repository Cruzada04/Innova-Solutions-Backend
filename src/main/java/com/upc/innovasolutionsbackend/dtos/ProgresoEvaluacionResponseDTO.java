package com.upc.innovasolutionsbackend.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgresoEvaluacionResponseDTO {
    private Long id;
    private Integer puntaje;
    private Integer medallasObtenidas;
    private LocalDateTime fechaEvaluacion;
    private String reporteGenerado;
    private UsuarioResponseDTO estudiante;
    private LeccionCustomResponseDTO leccion;
}
