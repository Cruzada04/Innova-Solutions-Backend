package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario insertar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepositorio.findAll();
    }

    public Usuario listarPorId(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Usuario actualizar(Usuario usuario) {
        if (usuarioRepositorio.existsById(usuario.getId())) {
            return usuarioRepositorio.save(usuario);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
