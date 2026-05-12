package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.UsuarioRequestDTO;
import com.upc.innovasolutionsbackend.dtos.UsuarioResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.servicios.UsuarioService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar datos críticos como el email y la contraseña al crear
    public UsuarioResponseDTO insertar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario = usuarioService.insertar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
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
    // Se agrega @Valid para asegurar que las actualizaciones mantengan la integridad de los datos
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario.setId(id);
        usuario = usuarioService.actualizar(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
