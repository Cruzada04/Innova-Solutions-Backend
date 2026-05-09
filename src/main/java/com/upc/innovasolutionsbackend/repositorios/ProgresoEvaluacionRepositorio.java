package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.ProgresoEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgresoEvaluacionRepositorio extends JpaRepository<ProgresoEvaluacion, Long> {
}
