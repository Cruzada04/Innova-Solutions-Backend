package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanSuscripcionResponseDTO {
    private Long id;
    private String nombre;
    private Double precio;
}
