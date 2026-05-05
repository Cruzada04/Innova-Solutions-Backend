package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.PerfilAprendizajeRequestDTO;
import com.upc.innovasolutionsbackend.dtos.PerfilAprendizajeResponseDTO;
import com.upc.innovasolutionsbackend.entidades.PerfilAprendizaje;
import com.upc.innovasolutionsbackend.servicios.PerfilAprendizajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/perfiles-aprendizaje")
public class PerfilAprendizajeController {
    @Autowired
    private PerfilAprendizajeService perfilService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public PerfilAprendizajeResponseDTO insertar(@RequestBody PerfilAprendizajeRequestDTO perfilRequestDTO) {
        PerfilAprendizaje perfil = modelMapper.map(perfilRequestDTO, PerfilAprendizaje.class);
        perfil = perfilService.insertar(perfil);
        return modelMapper.map(perfil, PerfilAprendizajeResponseDTO.class);
    }

    @GetMapping
    public List<PerfilAprendizajeResponseDTO> listar() {
        return perfilService.listar().stream()
                .map(perfil -> modelMapper.map(perfil, PerfilAprendizajeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PerfilAprendizajeResponseDTO listarPorId(@PathVariable Long id) {
        PerfilAprendizaje perfil = perfilService.listarPorId(id);
        return modelMapper.map(perfil, PerfilAprendizajeResponseDTO.class);
    }

    @PutMapping("/{id}")
    public PerfilAprendizajeResponseDTO actualizar(@PathVariable Long id, @RequestBody PerfilAprendizajeRequestDTO perfilRequestDTO) {
        PerfilAprendizaje perfil = modelMapper.map(perfilRequestDTO, PerfilAprendizaje.class);
        perfil.setId(id);
        perfil = perfilService.actualizar(perfil);
        return modelMapper.map(perfil, PerfilAprendizajeResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        perfilService.eliminar(id);
    }
}
