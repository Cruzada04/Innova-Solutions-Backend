package com.upc.innovasolutionsbackend.security.repositories;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Rol, Long> {
}
