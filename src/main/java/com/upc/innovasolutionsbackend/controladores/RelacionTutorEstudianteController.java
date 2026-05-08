package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.RelacionTutorEstudianteRequestDTO;
import com.upc.innovasolutionsbackend.dtos.RelacionTutorEstudianteResponseDTO;
import com.upc.innovasolutionsbackend.entidades.RelacionTutorEstudiante;
import com.upc.innovasolutionsbackend.servicios.RelacionTutorEstudianteService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/relaciones-tutor-estudiante")
public class RelacionTutorEstudianteController {
    @Autowired
    private RelacionTutorEstudianteService relacionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO al momento de la creación
    public RelacionTutorEstudianteResponseDTO insertar(@Valid @RequestBody RelacionTutorEstudianteRequestDTO relacionRequestDTO) {
        RelacionTutorEstudiante relacion = modelMapper.map(relacionRequestDTO, RelacionTutorEstudiante.class);
        relacion = relacionService.insertar(relacion);
        return modelMapper.map(relacion, RelacionTutorEstudianteResponseDTO.class);
    }

    @GetMapping
    public List<RelacionTutorEstudianteResponseDTO> listar() {
        return relacionService.listar().stream()
                .map(relacion -> modelMapper.map(relacion, RelacionTutorEstudianteResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RelacionTutorEstudianteResponseDTO listarPorId(@PathVariable Long id) {
        RelacionTutorEstudiante relacion = relacionService.listarPorId(id);
        return modelMapper.map(relacion, RelacionTutorEstudianteResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public RelacionTutorEstudianteResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody RelacionTutorEstudianteRequestDTO relacionRequestDTO) {
        RelacionTutorEstudiante relacion = modelMapper.map(relacionRequestDTO, RelacionTutorEstudiante.class);
        relacion.setId(id);
        relacion = relacionService.actualizar(relacion);
        return modelMapper.map(relacion, RelacionTutorEstudianteResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        relacionService.eliminar(id);
    }
}
