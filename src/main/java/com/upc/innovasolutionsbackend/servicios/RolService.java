package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepositorio rolRepositorio;

    @Transactional
    public Rol insertar(Rol rol) {
        return rolRepositorio.save(rol);
    }

    public List<Rol> listar() {
        return rolRepositorio.findAll();
    }

    public Rol listarPorId(Long id) {
        return rolRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Rol actualizar(Rol rol) {
        if (rolRepositorio.existsById(rol.getId())) {
            return rolRepositorio.save(rol);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        rolRepositorio.deleteById(id);
    }
}
