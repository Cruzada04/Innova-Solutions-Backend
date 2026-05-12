package com.upc.innovasolutionsbackend.dtos;

import jakarta.validation.constraints.NotNull; //
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelacionTutorEstudianteRequestDTO {

    @NotNull(message = "El ID del tutor es obligatorio") //
    private Long tutorId;

    @NotNull(message = "El ID del estudiante es obligatorio") //
    private Long estudianteId;
}
