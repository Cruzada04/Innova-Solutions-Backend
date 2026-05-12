package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.ElementoGuardado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementoGuardadoRepositorio extends JpaRepository<ElementoGuardado, Long> {
}
