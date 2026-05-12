package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementoGuardadoResponseDTO {
    private Long id;
    private String tipoElemento;
    private Long elementoId;
    private UsuarioResponseDTO usuario;
}
