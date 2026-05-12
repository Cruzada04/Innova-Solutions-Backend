package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Categoria;
import com.upc.innovasolutionsbackend.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Transactional
    public Categoria insertar(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> listar() {
        return categoriaRepositorio.findAll();
    }

    public Categoria listarPorId(Long id) {
        return categoriaRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Categoria actualizar(Categoria categoria) {
        if (categoriaRepositorio.existsById(categoria.getId())) {
            return categoriaRepositorio.save(categoria);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        categoriaRepositorio.deleteById(id);
    }
}
