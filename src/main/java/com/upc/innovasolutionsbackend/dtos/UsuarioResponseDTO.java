package com.upc.innovasolutionsbackend.dtos;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String nombreCompleto;
    private String username;
    private String correoElectronico;
    private String metodoRegistro;
    private RolResponseDTO rol;
    private List<RolResponseDTO> roles;
    private String fotoPerfil;
    private Long creadoPorId;
}
