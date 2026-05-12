package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilAprendizajeRequestDTO {

    @NotBlank(message = "El nivel de espectro es obligatorio")
    @Size(max = 50, message = "El nivel de espectro no puede exceder los 50 caracteres")
    private String nivelEspectro;

    @NotBlank(message = "Las necesidades específicas son obligatorias")
    @Size(max = 500, message = "Las necesidades no pueden exceder los 500 caracteres")
    private String necesidadesEspecificas;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;
}
