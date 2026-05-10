package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.ProgresoEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoEvaluacionRepositorio extends JpaRepository<ProgresoEvaluacion, Long> {

    @Query("SELECT FUNCTION('TO_CHAR', p.fechaEvaluacion, 'YYYY-MM'), COUNT(p), AVG(p.puntaje), SUM(p.medallasObtenidas) FROM ProgresoEvaluacion p GROUP BY FUNCTION('TO_CHAR', p.fechaEvaluacion, 'YYYY-MM') ORDER BY FUNCTION('TO_CHAR', p.fechaEvaluacion, 'YYYY-MM')")
    List<Object[]> contarPorMes();
}
