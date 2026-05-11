package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.TemaRequestDTO;
import com.upc.innovasolutionsbackend.dtos.TemaResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Tema;
import com.upc.innovasolutionsbackend.servicios.TemaService;
import jakarta.validation.Valid; // Importación necesaria
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/temas")
public class TemaController {
    @Autowired
    private TemaService temaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO antes de la inserción
    public TemaResponseDTO insertar(@Valid @RequestBody TemaRequestDTO temaRequestDTO) {
        Tema tema = modelMapper.map(temaRequestDTO, Tema.class);
        tema = temaService.insertar(tema);
        return modelMapper.map(tema, TemaResponseDTO.class);
    }

    @GetMapping
    public List<TemaResponseDTO> listar() {
        return temaService.listar().stream()
                .map(tema -> modelMapper.map(tema, TemaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TemaResponseDTO listarPorId(@PathVariable Long id) {
        Tema tema = temaService.listarPorId(id);
        return modelMapper.map(tema, TemaResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para validar los datos actualizados
    public TemaResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody TemaRequestDTO temaRequestDTO) {
        Tema tema = modelMapper.map(temaRequestDTO, Tema.class);
        tema.setId(id);
        tema = temaService.actualizar(tema);
        return modelMapper.map(tema, TemaResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        temaService.eliminar(id);
    }
}
