package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.dtos.FlashcardReporteDTO;
import com.upc.innovasolutionsbackend.entidades.Flashcard;
import com.upc.innovasolutionsbackend.entidades.OpcionRespuesta;
import com.upc.innovasolutionsbackend.repositorios.FlashcardRepositorio;
import com.upc.innovasolutionsbackend.repositorios.OpcionRespuestaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashcardService {
    @Autowired
    private FlashcardRepositorio flashcardRepositorio;
    @Autowired
    private OpcionRespuestaRepositorio opcionRespuestaRepositorio;

    @Transactional
    public Flashcard insertar(Flashcard flashcard) {
        return flashcardRepositorio.save(flashcard);
    }

    public List<Flashcard> listar() {
        return flashcardRepositorio.findAll();
    }

    public Flashcard listarPorId(Long id) {
        return flashcardRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Flashcard actualizar(Flashcard flashcard) {
        if (flashcardRepositorio.existsById(flashcard.getId())) {
            return flashcardRepositorio.save(flashcard);
        }
        return null;
    }

    @Transactional
    public Flashcard insertarConOpciones(Flashcard flashcard, List<OpcionRespuesta> opciones) {
        Flashcard guardada = flashcardRepositorio.save(flashcard);
        opciones.forEach(opcion -> opcion.setFlashcard(guardada));
        opcionRespuestaRepositorio.saveAll(opciones);
        return guardada;
    }

    @Transactional
    public void eliminar(Long id) {
        flashcardRepositorio.deleteById(id);
    }



    //reportes

    public List<FlashcardReporteDTO> reportePorDificultad() {
        return flashcardRepositorio.contarPorDificultad().stream()
                .map(row -> new FlashcardReporteDTO(
                        row[0] != null ? (String) row[0] : "Sin dificultad",
                        (Long) row[1]
                ))
                .collect(Collectors.toList());
    }

}
