package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionResponseDTO {
    private Long id;
    private Integer velocidadFlashcards;
    private String temaVisual;
    private UsuarioResponseDTO usuario;
}
