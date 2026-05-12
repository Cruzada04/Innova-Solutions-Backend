package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.PlanSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanSuscripcionRepositorio extends JpaRepository<PlanSuscripcion, Long> {
}
