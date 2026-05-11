package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.RelacionTutorEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionTutorEstudianteRepositorio extends JpaRepository<RelacionTutorEstudiante, Long> {
}
