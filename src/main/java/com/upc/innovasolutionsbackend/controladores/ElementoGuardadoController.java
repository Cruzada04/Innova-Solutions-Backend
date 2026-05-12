package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.ElementoGuardadoRequestDTO;
import com.upc.innovasolutionsbackend.dtos.ElementoGuardadoResponseDTO;
import com.upc.innovasolutionsbackend.entidades.ElementoGuardado;
import com.upc.innovasolutionsbackend.servicios.ElementoGuardadoService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/elementos-guardados")
public class ElementoGuardadoController {
    @Autowired
    private ElementoGuardadoService elementoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO al momento de la creación
    public ElementoGuardadoResponseDTO insertar(@Valid @RequestBody ElementoGuardadoRequestDTO elementoRequestDTO) {
        ElementoGuardado elemento = modelMapper.map(elementoRequestDTO, ElementoGuardado.class);
        elemento = elementoService.insertar(elemento);
        return modelMapper.map(elemento, ElementoGuardadoResponseDTO.class);
    }

    @GetMapping
    public List<ElementoGuardadoResponseDTO> listar() {
        return elementoService.listar().stream()
                .map(elemento -> modelMapper.map(elemento, ElementoGuardadoResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ElementoGuardadoResponseDTO listarPorId(@PathVariable Long id) {
        ElementoGuardado elemento = elementoService.listarPorId(id);
        return modelMapper.map(elemento, ElementoGuardadoResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public ElementoGuardadoResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ElementoGuardadoRequestDTO elementoRequestDTO) {
        ElementoGuardado elemento = modelMapper.map(elementoRequestDTO, ElementoGuardado.class);
        elemento.setId(id);
        elemento = elementoService.actualizar(elemento);
        return modelMapper.map(elemento, ElementoGuardadoResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        elementoService.eliminar(id);
    }
}
