package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponseDTO {
    private Long id;
    private String nombre;
    private String iconoUrl;
}
