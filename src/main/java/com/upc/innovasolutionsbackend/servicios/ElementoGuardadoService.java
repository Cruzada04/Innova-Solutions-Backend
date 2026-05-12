package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.ElementoGuardado;
import com.upc.innovasolutionsbackend.repositorios.ElementoGuardadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ElementoGuardadoService {
    @Autowired
    private ElementoGuardadoRepositorio elementoGuardadoRepositorio;

    @Transactional
    public ElementoGuardado insertar(ElementoGuardado elemento) {
        return elementoGuardadoRepositorio.save(elemento);
    }

    public List<ElementoGuardado> listar() {
        return elementoGuardadoRepositorio.findAll();
    }

    public ElementoGuardado listarPorId(Long id) {
        return elementoGuardadoRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public ElementoGuardado actualizar(ElementoGuardado elemento) {
        if (elementoGuardadoRepositorio.existsById(elemento.getId())) {
            return elementoGuardadoRepositorio.save(elemento);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        elementoGuardadoRepositorio.deleteById(id);
    }
}
