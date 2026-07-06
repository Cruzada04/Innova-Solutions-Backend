package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.RegistroAlumnoRequestDTO;
import com.upc.innovasolutionsbackend.dtos.UsuarioRequestDTO;
import com.upc.innovasolutionsbackend.dtos.UsuarioResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.repositorios.RolRepositorio;
import com.upc.innovasolutionsbackend.repositorios.UsuarioRepositorio;
import com.upc.innovasolutionsbackend.servicios.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public UsuarioResponseDTO insertar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        if (usuarioRequestDTO.getRolId() != null) {
            Rol rol = rolRepositorio.findById(usuarioRequestDTO.getRolId()).orElse(null);
            usuario.setRol(rol);
        }
        
        if (usuarioRequestDTO.getProfesorUsername() != null && !usuarioRequestDTO.getProfesorUsername().isEmpty()) {
            Usuario profesor = usuarioRepositorio.findByUsername(usuarioRequestDTO.getProfesorUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado: " + usuarioRequestDTO.getProfesorUsername()));
            usuario.setCreadoPor(profesor);
        }
        
        usuario = usuarioService.insertar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @PostMapping("/registro-alumno")
    @PreAuthorize("hasAnyRole('PROFESOR', 'PADRE')")
    public UsuarioResponseDTO registrarAlumno(
            @Valid @RequestBody RegistroAlumnoRequestDTO request,
            Authentication auth) {
        Usuario estudiante = usuarioService.registrarAlumno(request, auth.getName());
        return modelMapper.map(estudiante, UsuarioResponseDTO.class);
    }

    @GetMapping("/maestro/dashboard-stats")
    @PreAuthorize("hasAnyRole('PROFESOR', 'PADRE')")
    public com.upc.innovasolutionsbackend.dtos.MaestroDashboardStatsDTO dashboardStats(org.springframework.security.core.Authentication auth) {
        Usuario user = usuarioRepositorio.findByUsername(auth.getName())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return usuarioService.obtenerStatsMaestro(user.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESOR', 'PADRE')")
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listar().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO listarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.listarPorId(id);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESOR', 'PADRE')")
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario.setId(id);
        usuario = usuarioService.actualizar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return org.springframework.http.ResponseEntity.badRequest().body(java.util.Map.of("message", ex.getMessage()));
    }
}
