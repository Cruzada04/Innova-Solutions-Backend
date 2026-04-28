package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.PerfilAprendizaje;
import com.upc.innovasolutionsbackend.repositorios.PerfilAprendizajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfilAprendizajeService {
    @Autowired
    private PerfilAprendizajeRepositorio perfilAprendizajeRepositorio;

    @Transactional
    public PerfilAprendizaje insertar(PerfilAprendizaje perfil) {
        return perfilAprendizajeRepositorio.save(perfil);
    }

    public List<PerfilAprendizaje> listar() {
        return perfilAprendizajeRepositorio.findAll();
    }

    public PerfilAprendizaje listarPorId(Long id) {
        return perfilAprendizajeRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public PerfilAprendizaje actualizar(PerfilAprendizaje perfil) {
        if (perfilAprendizajeRepositorio.existsById(perfil.getId())) {
            return perfilAprendizajeRepositorio.save(perfil);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        perfilAprendizajeRepositorio.deleteById(id);
    }
}
