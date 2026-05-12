package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelacionTutorEstudianteResponseDTO {
    private Long id;
    private UsuarioResponseDTO tutor;
    private UsuarioResponseDTO estudiante;
}
