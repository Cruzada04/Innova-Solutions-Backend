package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario insertar(Usuario usuario) {
        // Encriptar contraseña antes de guardar
        if (usuario.getContrasena() != null) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }

        // Sincronizar el rol ManyToOne con el Set ManyToMany para Spring Security
        if (usuario.getRol() != null) {
            usuario.setRoles(Collections.singleton(usuario.getRol()));
        }

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
        Usuario existente = usuarioRepositorio.findById(usuario.getId()).orElse(null);
        if (existente != null) {
            // Si no se cambia la contraseña, el frontend manda "dummyPassword123"
            if (usuario.getContrasena() == null || usuario.getContrasena().equals("dummyPassword123")) {
                usuario.setContrasena(existente.getContrasena());
            } else {
                // Si es una nueva contraseña, la encriptamos
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }

            // Sincronizar el rol ManyToOne con el Set ManyToMany para Spring Security
            if (usuario.getRol() != null) {
                usuario.setRoles(Collections.singleton(usuario.getRol()));
            }

            return usuarioRepositorio.save(usuario);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
