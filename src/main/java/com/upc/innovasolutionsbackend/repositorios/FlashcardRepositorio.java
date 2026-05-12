package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepositorio extends JpaRepository<Flashcard, Long> {


    @Query("SELECT f.leccion.dificultad, COUNT(f) FROM Flashcard f WHERE f.leccion IS NOT NULL GROUP BY f.leccion.dificultad")
    List<Object[]> contarPorDificultad();
}