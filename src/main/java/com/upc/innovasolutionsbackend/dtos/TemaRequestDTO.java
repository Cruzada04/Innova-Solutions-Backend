package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemaRequestDTO {
    private String nombre;
    private Long categoriaId;
}
