package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.ResenaUsuarioRequestDTO;
import com.upc.innovasolutionsbackend.dtos.ResenaUsuarioResponseDTO;
import com.upc.innovasolutionsbackend.entidades.ResenaUsuario;
import com.upc.innovasolutionsbackend.servicios.ResenaUsuarioService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resenas")
public class ResenaUsuarioController {
    @Autowired
    private ResenaUsuarioService resenaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO al momento de la creación
    public ResenaUsuarioResponseDTO insertar(@Valid @RequestBody ResenaUsuarioRequestDTO resenaRequestDTO) {
        ResenaUsuario resena = modelMapper.map(resenaRequestDTO, ResenaUsuario.class);
        resena = resenaService.insertar(resena);
        return modelMapper.map(resena, ResenaUsuarioResponseDTO.class);
    }

    @GetMapping
    public List<ResenaUsuarioResponseDTO> listar() {
        return resenaService.listar().stream()
                .map(resena -> modelMapper.map(resena, ResenaUsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResenaUsuarioResponseDTO listarPorId(@PathVariable Long id) {
        ResenaUsuario resena = resenaService.listarPorId(id);
        return modelMapper.map(resena, ResenaUsuarioResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public ResenaUsuarioResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ResenaUsuarioRequestDTO resenaRequestDTO) {
        ResenaUsuario resena = modelMapper.map(resenaRequestDTO, ResenaUsuario.class);
        resena.setId(id);
        resena = resenaService.actualizar(resena);
        return modelMapper.map(resena, ResenaUsuarioResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
    }
}
