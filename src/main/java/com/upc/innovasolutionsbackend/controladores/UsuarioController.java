package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.UsuarioRequestDTO;
import com.upc.innovasolutionsbackend.dtos.UsuarioResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.servicios.UsuarioService;
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
    public UsuarioResponseDTO insertar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
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
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
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
