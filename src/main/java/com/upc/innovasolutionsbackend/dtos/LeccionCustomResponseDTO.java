package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeccionCustomResponseDTO {
    private Long id;
    private String titulo;
    private String dificultad;
    private UsuarioResponseDTO creador;
    private UsuarioResponseDTO estudiante;
    private TemaResponseDTO tema;
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("leccion")
    private java.util.List<FlashcardResponseDTO> flashcards;
}
