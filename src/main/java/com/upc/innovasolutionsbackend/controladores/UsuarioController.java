package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.RegistroAlumnoPublicRequestDTO;
import com.upc.innovasolutionsbackend.dtos.RegistroAlumnoRequestDTO;
import com.upc.innovasolutionsbackend.dtos.RegistroPadreRequestDTO;
import com.upc.innovasolutionsbackend.dtos.UsuarioRequestDTO;
import com.upc.innovasolutionsbackend.dtos.UsuarioResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.repositorios.RolRepositorio;
import com.upc.innovasolutionsbackend.servicios.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private ModelMapper modelMapper;

    private static final String ROL_PROFESOR = "PROFESOR";

    @PostMapping
    public UsuarioResponseDTO insertar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Rol rol = rolRepositorio.findById(dto.getRolId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol especificado no existe"));

        if (ROL_PROFESOR.equalsIgnoreCase(rol.getNombre()) && dto.getPlanSuscripcionId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El plan de suscripción es obligatorio para usuarios con rol PROFESOR");
        }

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario = usuarioService.insertar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @PostMapping("/registro-padre")
    public UsuarioResponseDTO registrarPadre(@Valid @RequestBody RegistroPadreRequestDTO dto) {
        Usuario padre = usuarioService.registrarPadre(dto);
        return modelMapper.map(padre, UsuarioResponseDTO.class);
    }

    @PostMapping("/registro-alumno-public")
    public UsuarioResponseDTO registrarAlumnoPublic(@Valid @RequestBody RegistroAlumnoPublicRequestDTO dto) {
        Usuario alumno = usuarioService.registrarAlumnoPublic(dto);
        return modelMapper.map(alumno, UsuarioResponseDTO.class);
    }

    @PostMapping("/registro-alumno")
    public UsuarioResponseDTO registrarAlumno(@Valid @RequestBody RegistroAlumnoRequestDTO dto,
                                               Authentication authentication) {
        boolean esPadre = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("ROLE_PADRE"));
        if (!esPadre) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo un usuario con rol PADRE puede registrar alumnos");
        }

        String usernamePadre = authentication.getName();
        Usuario padre = usuarioService.listarPorUsername(usernamePadre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        Usuario alumno = usuarioService.registrarAlumno(dto, padre.getId());
        return modelMapper.map(alumno, UsuarioResponseDTO.class);
    }

    @GetMapping
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
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setId(id);
        usuario = usuarioService.actualizar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
