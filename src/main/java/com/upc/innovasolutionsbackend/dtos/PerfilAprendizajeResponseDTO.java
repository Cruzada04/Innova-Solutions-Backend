package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilAprendizajeResponseDTO {
    private Long id;
    private String nivelEspectro;
    private String necesidadesEspecificas;
    private UsuarioResponseDTO estudiante;
}
