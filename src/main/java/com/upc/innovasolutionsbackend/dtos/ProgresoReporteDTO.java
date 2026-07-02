package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgresoReporteDTO {
    private String mes;
    private Long cantidadEvaluaciones;
    private Double puntajePromedio;
    private Long medallasTotales;
}