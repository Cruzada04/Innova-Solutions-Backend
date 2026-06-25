package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.LeccionCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeccionCustomRepositorio extends JpaRepository<LeccionCustom, Long> {
    @Query("SELECT l.dificultad, COUNT(l) FROM LeccionCustom l GROUP BY l.dificultad")
    List<Object[]> contarPorDificultad();

    List<LeccionCustom> findByEstudianteId(Long estudianteId);
}
