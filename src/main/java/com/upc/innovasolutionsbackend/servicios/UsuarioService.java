package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.entidades.PlanSuscripcion;
import com.upc.innovasolutionsbackend.repositorios.UsuarioRepositorio;
import com.upc.innovasolutionsbackend.repositorios.LeccionCustomRepositorio;
import com.upc.innovasolutionsbackend.repositorios.FlashcardRepositorio;
import com.upc.innovasolutionsbackend.repositorios.TemaRepositorio;
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

    @Autowired
    private LeccionCustomRepositorio leccionCustomRepositorio;

    @Autowired
    private FlashcardRepositorio flashcardRepositorio;

    @Autowired
    private TemaRepositorio temaRepositorio;

    public com.upc.innovasolutionsbackend.dtos.MaestroDashboardStatsDTO obtenerStatsMaestro(Long teacherId) {
        long totalAlumnos = usuarioRepositorio.contarAlumnosPorMaestro(teacherId);
        long totalLecciones = leccionCustomRepositorio.contarLeccionesPorMaestro(teacherId);
        long totalFlashcards = flashcardRepositorio.contarFlashcardsPorMaestro(teacherId);
        long totalTemas = temaRepositorio.count();
        return new com.upc.innovasolutionsbackend.dtos.MaestroDashboardStatsDTO(totalAlumnos, totalTemas, totalLecciones, totalFlashcards);
    }

    @Transactional
    public Usuario insertar(Usuario usuario) {
        if (usuarioRepositorio.findByUsername(usuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        
        // Encriptar contraseña antes de guardar
        if (usuario.getContrasena() != null) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }

        // Asignar plan por defecto (Plan Gratuito) si es nulo
        if (usuario.getPlanSuscripcion() == null) {
            PlanSuscripcion plan = new PlanSuscripcion();
            plan.setId(1L);
            usuario.setPlanSuscripcion(plan);
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
            Usuario existenteByUsername = usuarioRepositorio.findByUsername(usuario.getUsername()).orElse(null);
            if (existenteByUsername != null && !existenteByUsername.getId().equals(usuario.getId())) {
                throw new IllegalArgumentException("El usuario ya existe");
            }

            // Copiar campos editables del DTO al objeto persistente existente
            existente.setNombreCompleto(usuario.getNombreCompleto());
            existente.setUsername(usuario.getUsername());
            existente.setCorreoElectronico(usuario.getCorreoElectronico());
            existente.setMetodoRegistro(usuario.getMetodoRegistro());
            existente.setRol(usuario.getRol());
            existente.setPlanSuscripcion(usuario.getPlanSuscripcion());

            // Si no se cambia la contraseña, el frontend manda "dummyPassword123"
            if (usuario.getContrasena() != null && !usuario.getContrasena().equals("dummyPassword123")) {
                existente.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }

            // Sincronizar el rol ManyToOne con el Set ManyToMany para Spring Security
            if (existente.getRol() != null) {
                existente.setRoles(Collections.singleton(existente.getRol()));
            }

            return usuarioRepositorio.save(existente);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional
    public Usuario registrarAlumno(com.upc.innovasolutionsbackend.dtos.RegistroAlumnoRequestDTO request, String tutorUsername) {
        if (usuarioRepositorio.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        
        Usuario tutor = usuarioRepositorio.findByUsername(tutorUsername)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
        Usuario estudiante = new Usuario();
        estudiante.setNombreCompleto(request.getUsername());
        estudiante.setUsername(request.getUsername());
        estudiante.setContrasena(passwordEncoder.encode(request.getPin()));
        estudiante.setCorreoElectronico(request.getUsername() + "@student.innova.com");
        estudiante.setMetodoRegistro("PADRE");
        
        Rol rolEstudiante = new Rol();
        rolEstudiante.setId(3L);
        estudiante.setRol(rolEstudiante);
        estudiante.setRoles(java.util.Collections.singleton(rolEstudiante));
        estudiante.setCreadoPor(tutor);
        
        PlanSuscripcion plan = new PlanSuscripcion();
        plan.setId(1L);
        estudiante.setPlanSuscripcion(plan);
        
        estudiante = usuarioRepositorio.save(estudiante);
        estudiante.setCreadoPorId(tutor.getId());
        return estudiante;
    }
}
