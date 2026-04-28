package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    private String nombreCompleto;
    private String correoElectronico;
    private String contrasena;
    private String metodoRegistro;
    private Long rolId;
    private Long planId;
}
