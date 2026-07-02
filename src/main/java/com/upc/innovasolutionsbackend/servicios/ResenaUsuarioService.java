package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.ResenaUsuario;
import com.upc.innovasolutionsbackend.repositorios.ResenaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResenaUsuarioService {
    @Autowired
    private ResenaUsuarioRepositorio resenaUsuarioRepositorio;

    @Transactional
    public ResenaUsuario insertar(ResenaUsuario resena) {
        return resenaUsuarioRepositorio.save(resena);
    }

    public List<ResenaUsuario> listar() {
        return resenaUsuarioRepositorio.findAll();
    }

    public ResenaUsuario listarPorId(Long id) {
        return resenaUsuarioRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public ResenaUsuario actualizar(ResenaUsuario resena) {
        if (resenaUsuarioRepositorio.existsById(resena.getId())) {
            return resenaUsuarioRepositorio.save(resena);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        resenaUsuarioRepositorio.deleteById(id);
    }
}
