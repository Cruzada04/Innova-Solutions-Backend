package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Tema;
import com.upc.innovasolutionsbackend.repositorios.TemaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemaService {
    @Autowired
    private TemaRepositorio temaRepositorio;

    @Transactional
    public Tema insertar(Tema tema) {
        return temaRepositorio.save(tema);
    }

    public List<Tema> listar() {
        return temaRepositorio.findAll();
    }

    public Tema listarPorId(Long id) {
        return temaRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Tema actualizar(Tema tema) {
        if (temaRepositorio.existsById(tema.getId())) {
            return temaRepositorio.save(tema);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        temaRepositorio.deleteById(id);
    }
}
