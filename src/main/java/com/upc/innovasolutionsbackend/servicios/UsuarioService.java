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
            // Preservar la contraseña original si la recibida es vacía, nula o el dummy placeholder
            if (usuario.getContrasena() == null || 
                usuario.getContrasena().trim().isEmpty() || 
                usuario.getContrasena().equals("dummyPassword123")) {
                usuario.setContrasena(existente.getContrasena());
            } else {
                // Encriptar la nueva contraseña
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }

            // Sincronizar roles
            if (usuario.getRol() != null && usuario.getRol().getId() != null) {
                usuario.setRoles(Collections.singleton(usuario.getRol()));
            } else {
                usuario.setRol(existente.getRol());
                usuario.setRoles(existente.getRoles());
            }

            // Preservar plan de suscripción
            if (usuario.getPlanSuscripcion() == null || usuario.getPlanSuscripcion().getId() == null) {
                usuario.setPlanSuscripcion(existente.getPlanSuscripcion());
            }

            // Preservar método de registro
            if (usuario.getMetodoRegistro() == null || usuario.getMetodoRegistro().trim().isEmpty()) {
                usuario.setMetodoRegistro(existente.getMetodoRegistro());
            }

            // Actualizar o preservar foto de perfil
            if (usuario.getFotoPerfil() == null) {
                usuario.setFotoPerfil(existente.getFotoPerfil());
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
