package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementoGuardadoRequestDTO {

    @NotBlank(message = "El tipo de elemento es obligatorio")
    private String tipoElemento;

    @NotNull(message = "El ID del elemento es obligatorio")
    private Long elementoId;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;
}
