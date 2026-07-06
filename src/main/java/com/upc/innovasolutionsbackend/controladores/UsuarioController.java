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
    // Se agrega @Valid para validar datos críticos como el email y la contraseña al crear
    public UsuarioResponseDTO insertar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario = usuarioService.insertar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @PostMapping("/registro-alumno")
    @PreAuthorize("hasAnyRole('PROFESOR', 'PADRE')")
    public UsuarioResponseDTO registrarAlumno(@Valid @RequestBody RegistroAlumnoRequestDTO dto, Authentication auth) {
        String usernameParent = auth.getName();
        Usuario usuario = usuarioRepositorio.findByUsername(usernameParent)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rolAlumno = rolRepositorio.findById(3L)
                .orElseThrow(() -> new RuntimeException("Rol ALUMNO no encontrado"));

        Usuario alumno = new Usuario();
        alumno.setUsername(dto.getUsername());
        alumno.setContrasena(dto.getPin());
        alumno.setNombreCompleto(dto.getUsername());
        alumno.setCorreoElectronico(dto.getUsername() + "@student.innova.com");
        alumno.setMetodoRegistro("PADRE");
        alumno.setRol(rolAlumno);
        alumno.setCreadoPor(usuario);

        alumno = usuarioService.insertar(alumno);
        return modelMapper.map(alumno, UsuarioResponseDTO.class);
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
}
