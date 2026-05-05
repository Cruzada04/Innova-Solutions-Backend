package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionRequestDTO {
    private Integer velocidadFlashcards;
    private String temaVisual;
    private Long usuarioId;
}
