package com.upc.innovasolutionsbackend.dtos;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardResponseDTO {
    private Long id;
    private String preguntaTexto;
    private String imagenUrl;
    private String colorFondo;
    private String colorTexto;
    private LeccionCustomResponseDTO leccion;
    private List<OpcionRespuestaItemDTO> opciones;
    private String respuestaCorrecta;
}
