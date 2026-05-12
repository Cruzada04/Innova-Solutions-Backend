package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String nombreCompleto;
    private String username;
    private String contrasena;
    private String correoElectronico;
    private String metodoRegistro;
    private RolResponseDTO rol;
}
