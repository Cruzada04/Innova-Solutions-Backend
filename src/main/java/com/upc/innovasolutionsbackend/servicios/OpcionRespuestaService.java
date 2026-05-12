package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.OpcionRespuesta;
import com.upc.innovasolutionsbackend.repositorios.OpcionRespuestaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OpcionRespuestaService {
    @Autowired
    private OpcionRespuestaRepositorio opcionRespuestaRepositorio;

    @Transactional
    public OpcionRespuesta insertar(OpcionRespuesta opcion) {
        return opcionRespuestaRepositorio.save(opcion);
    }

    public List<OpcionRespuesta> listar() {
        return opcionRespuestaRepositorio.findAll();
    }

    public OpcionRespuesta listarPorId(Long id) {
        return opcionRespuestaRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public OpcionRespuesta actualizar(OpcionRespuesta opcion) {
        if (opcionRespuestaRepositorio.existsById(opcion.getId())) {
            return opcionRespuestaRepositorio.save(opcion);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        opcionRespuestaRepositorio.deleteById(id);
    }
}
