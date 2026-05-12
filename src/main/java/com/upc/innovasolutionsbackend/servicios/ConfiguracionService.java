package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Configuracion;
import com.upc.innovasolutionsbackend.repositorios.ConfiguracionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConfiguracionService {
    @Autowired
    private ConfiguracionRepositorio configuracionRepositorio;

    @Transactional
    public Configuracion insertar(Configuracion configuracion) {
        return configuracionRepositorio.save(configuracion);
    }

    public List<Configuracion> listar() {
        return configuracionRepositorio.findAll();
    }

    public Configuracion listarPorId(Long id) {
        return configuracionRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Configuracion actualizar(Configuracion configuracion) {
        if (configuracionRepositorio.existsById(configuracion.getId())) {
            return configuracionRepositorio.save(configuracion);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        configuracionRepositorio.deleteById(id);
    }
}
