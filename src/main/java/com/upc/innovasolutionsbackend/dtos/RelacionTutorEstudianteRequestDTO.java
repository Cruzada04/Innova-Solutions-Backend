package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelacionTutorEstudianteRequestDTO {
    private Long tutorId;
    private Long estudianteId;
}
