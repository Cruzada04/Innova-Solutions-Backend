package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilAprendizajeRequestDTO {
    private String nivelEspectro;
    private String necesidadesEspecificas;
    private Long estudianteId;
}
