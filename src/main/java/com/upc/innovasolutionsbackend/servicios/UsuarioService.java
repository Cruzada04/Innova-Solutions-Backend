package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.dtos.RegistroAlumnoPublicRequestDTO;
import com.upc.innovasolutionsbackend.dtos.RegistroAlumnoRequestDTO;
import com.upc.innovasolutionsbackend.dtos.RegistroPadreRequestDTO;
import com.upc.innovasolutionsbackend.entidades.PlanSuscripcion;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.repositorios.PlanSuscripcionRepositorio;
import com.upc.innovasolutionsbackend.repositorios.RolRepositorio;
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
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlanSuscripcionRepositorio planSuscripcionRepositorio;

    private static final String ROL_PROFESOR = "PROFESOR";
    private static final String ROL_PADRE = "PADRE";
    private static final String ROL_ALUMNO = "ALUMNO";

    @Transactional
    public Usuario insertar(Usuario usuario) {
        if (usuario.getContrasena() != null) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }

        if (usuario.getRol() != null && usuario.getRol().getId() != null) {
            Rol rol = rolRepositorio.findById(usuario.getRol().getId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
            usuario.setRoles(Collections.singleton(rol));
        }

        if (usuario.getPlanSuscripcion() != null && usuario.getPlanSuscripcion().getId() != null) {
            PlanSuscripcion plan = planSuscripcionRepositorio.findById(usuario.getPlanSuscripcion().getId())
                    .orElse(null);
            usuario.setPlanSuscripcion(plan);
        }

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public Usuario registrarPadre(RegistroPadreRequestDTO dto) {
        Rol rolPadre = rolRepositorio.findByNombreIgnoreCase(ROL_PADRE)
                .orElseThrow(() -> new RuntimeException("El rol PADRE no existe en el sistema"));

        Usuario profesor = usuarioRepositorio.findByUsername(dto.getUsernameProfesor())
                .orElseThrow(() -> new RuntimeException("No existe un profesor con el username: " + dto.getUsernameProfesor()));

        Rol rolProfesor = rolRepositorio.findByNombreIgnoreCase(ROL_PROFESOR)
                .orElseThrow(() -> new RuntimeException("El rol PROFESOR no existe en el sistema"));

        boolean esProfesor = profesor.getRol() != null && profesor.getRol().getId().equals(rolProfesor.getId());
        if (!esProfesor) {
            throw new RuntimeException("El usuario " + dto.getUsernameProfesor() + " no tiene el rol de PROFESOR");
        }

        Usuario padre = new Usuario();
        padre.setNombreCompleto(dto.getNombreCompleto());
        padre.setCorreoElectronico(dto.getCorreoElectronico());
        padre.setUsername(dto.getUsername());
        padre.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        padre.setMetodoRegistro(dto.getMetodoRegistro());
        padre.setRol(rolPadre);
        padre.setRoles(Collections.singleton(rolPadre));
        padre.setCreadoPor(profesor);

        return usuarioRepositorio.save(padre);
    }

    @Transactional
    public Usuario registrarAlumno(RegistroAlumnoRequestDTO dto, Long padreId) {
        Usuario padre = usuarioRepositorio.findById(padreId)
                .orElseThrow(() -> new RuntimeException("El usuario Padre no existe"));

        Rol rolAlumno = rolRepositorio.findByNombreIgnoreCase(ROL_ALUMNO)
                .orElseThrow(() -> new RuntimeException("El rol ALUMNO no existe en el sistema"));

        Usuario alumno = new Usuario();
        alumno.setNombreCompleto(dto.getUsername());
        alumno.setUsername(dto.getUsername());
        alumno.setContrasena(passwordEncoder.encode(dto.getPin()));
        alumno.setMetodoRegistro("PADRE");
        alumno.setRol(rolAlumno);
        alumno.setRoles(Collections.singleton(rolAlumno));
        alumno.setCreadoPor(padre);

        return usuarioRepositorio.save(alumno);
    }

    @Transactional
    public Usuario registrarAlumnoPublic(RegistroAlumnoPublicRequestDTO dto) {
        Usuario padre = usuarioRepositorio.findByUsername(dto.getUsernamePadre())
                .orElseThrow(() -> new RuntimeException("El usuario del padre no es válido"));

        Rol rolPadre = rolRepositorio.findByNombreIgnoreCase(ROL_PADRE)
                .orElseThrow(() -> new RuntimeException("El rol PADRE no existe en el sistema"));

        boolean esPadre = padre.getRol() != null && padre.getRol().getId().equals(rolPadre.getId());
        if (!esPadre) {
            throw new RuntimeException("El usuario del padre no es válido");
        }

        Rol rolAlumno = rolRepositorio.findByNombreIgnoreCase(ROL_ALUMNO)
                .orElseThrow(() -> new RuntimeException("El rol ALUMNO no existe en el sistema"));

        Usuario alumno = new Usuario();
        alumno.setNombreCompleto(dto.getNombreCompleto());
        alumno.setUsername(dto.getUsername());
        alumno.setContrasena(passwordEncoder.encode(dto.getPin()));
        alumno.setMetodoRegistro("PADRE");
        alumno.setRol(rolAlumno);
        alumno.setRoles(Collections.singleton(rolAlumno));
        alumno.setCreadoPor(padre);

        return usuarioRepositorio.save(alumno);
    }

    public List<Usuario> listar() {
        return usuarioRepositorio.findAll();
    }

    public Usuario listarPorId(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public java.util.Optional<Usuario> listarPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
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
                Rol rol = rolRepositorio.findById(usuario.getRol().getId())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
                usuario.setRol(rol);
                usuario.setRoles(Collections.singleton(rol));
            } else {
                usuario.setRol(existente.getRol());
                usuario.setRoles(existente.getRoles());
            }

            // Preservar/actualizar plan de suscripción
            if (usuario.getPlanSuscripcion() == null || usuario.getPlanSuscripcion().getId() == null) {
                usuario.setPlanSuscripcion(existente.getPlanSuscripcion());
            } else {
                PlanSuscripcion plan = planSuscripcionRepositorio.findById(usuario.getPlanSuscripcion().getId())
                        .orElse(null);
                usuario.setPlanSuscripcion(plan);
            }

            // Preservar método de registro
            if (usuario.getMetodoRegistro() == null || usuario.getMetodoRegistro().trim().isEmpty()) {
                usuario.setMetodoRegistro(existente.getMetodoRegistro());
            }

            // Actualizar o preservar foto de perfil
            if (usuario.getFotoPerfil() == null) {
                usuario.setFotoPerfil(existente.getFotoPerfil());
            }

            // Preservar creadoPor
            if (usuario.getCreadoPor() == null) {
                usuario.setCreadoPor(existente.getCreadoPor());
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
