package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepositorio extends JpaRepository<Tema, Long> {
}
