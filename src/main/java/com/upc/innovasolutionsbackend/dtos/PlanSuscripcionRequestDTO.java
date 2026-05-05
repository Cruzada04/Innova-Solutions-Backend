package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanSuscripcionRequestDTO {
    private String nombre;
    private Double precio;
}
