package com.upc.innovasolutionsbackend.repositorios;

import com.upc.innovasolutionsbackend.entidades.ResenaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaUsuarioRepositorio extends JpaRepository<ResenaUsuario, Long> {
}
