package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemaResponseDTO {
    private Long id;
    private String nombre;
    private CategoriaResponseDTO categoria;
}
