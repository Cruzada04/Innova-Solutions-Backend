package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementoGuardadoRequestDTO {
    private String tipoElemento;
    private Long elementoId;
    private Long usuarioId;
}
