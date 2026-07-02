package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.FlashcardRequestDTO;
import com.upc.innovasolutionsbackend.dtos.FlashcardResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Flashcard;
import com.upc.innovasolutionsbackend.entidades.LeccionCustom;
import com.upc.innovasolutionsbackend.entidades.OpcionRespuesta;
import com.upc.innovasolutionsbackend.servicios.FlashcardService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.upc.innovasolutionsbackend.dtos.FlashcardConOpcionesRequestDTO;
import com.upc.innovasolutionsbackend.dtos.FlashcardReporteDTO;


import com.upc.innovasolutionsbackend.dtos.OpcionRespuestaItemDTO;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public FlashcardResponseDTO insertar(@Valid @RequestBody FlashcardRequestDTO flashcardRequestDTO) {
        Flashcard flashcard = modelMapper.map(flashcardRequestDTO, Flashcard.class);
        flashcard = flashcardService.insertar(flashcard);
        return modelMapper.map(flashcard, FlashcardResponseDTO.class);
    }

    @PostMapping("/con-opciones")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public FlashcardResponseDTO insertarConOpciones(@Valid @RequestBody FlashcardConOpcionesRequestDTO dto) {

        Flashcard flashcard = new Flashcard();
        flashcard.setPreguntaTexto(dto.getPreguntaTexto());
        flashcard.setImagenUrl(dto.getImagenUrl());
        flashcard.setColorFondo(dto.getColorFondo());
        flashcard.setColorTexto(dto.getColorTexto());

        LeccionCustom leccion = new LeccionCustom();
        leccion.setId(dto.getLeccionId());
        flashcard.setLeccion(leccion);

        List<OpcionRespuesta> opciones = dto.getOpciones().stream()
                .map(itemDto -> {
                    OpcionRespuesta opcion = new OpcionRespuesta();
                    opcion.setTextoOpcion(itemDto.getTextoOpcion());
                    opcion.setEsCorrecta(itemDto.getEsCorrecta());
                    opcion.setFeedbackRespuesta(itemDto.getFeedbackRespuesta());
                    return opcion;
                })
                .collect(Collectors.toList());

        Flashcard guardada = flashcardService.insertarConOpciones(flashcard, opciones);
        return modelMapper.map(guardada, FlashcardResponseDTO.class);
    }

    private FlashcardResponseDTO toDto(Flashcard f) {
        FlashcardResponseDTO dto = modelMapper.map(f, FlashcardResponseDTO.class);
        if (f.getOpciones() != null) {
            dto.setOpciones(f.getOpciones().stream().map(o ->
                new OpcionRespuestaItemDTO(o.getTextoOpcion(), o.getEsCorrecta(), o.getFeedbackRespuesta())
            ).collect(Collectors.toList()));
            dto.setRespuestaCorrecta(f.getOpciones().stream()
                .filter(OpcionRespuesta::getEsCorrecta)
                .findFirst().map(OpcionRespuesta::getTextoOpcion).orElse(null));
        }
        return dto;
    }

    @GetMapping
    public List<FlashcardResponseDTO> listar() {
        return flashcardService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FlashcardResponseDTO listarPorId(@PathVariable Long id) {
        return toDto(flashcardService.listarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public FlashcardResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody FlashcardRequestDTO flashcardRequestDTO) {
        Flashcard flashcard = modelMapper.map(flashcardRequestDTO, Flashcard.class);
        flashcard.setId(id);
        flashcard = flashcardService.actualizar(flashcard);
        return modelMapper.map(flashcard, FlashcardResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public void eliminar(@PathVariable Long id) {
        flashcardService.eliminar(id);
    }




    @GetMapping("/reporte/dificultad")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public List<FlashcardReporteDTO> reportePorDificultad() {
        return flashcardService.reportePorDificultad();
    }


}
