package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.FlashcardReporteDTO;
import com.upc.innovasolutionsbackend.dtos.LeccionCustomRequestDTO;
import com.upc.innovasolutionsbackend.dtos.LeccionCustomResponseDTO;
import com.upc.innovasolutionsbackend.entidades.LeccionCustom;
import com.upc.innovasolutionsbackend.servicios.LeccionCustomService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lecciones-custom")
public class LeccionCustomController {
    @Autowired
    private LeccionCustomService leccionCustomService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para activar las validaciones definidas en el DTO al insertar
    public LeccionCustomResponseDTO insertar(@Valid @RequestBody LeccionCustomRequestDTO leccionRequestDTO) {
        LeccionCustom leccion = modelMapper.map(leccionRequestDTO, LeccionCustom.class);
        leccion = leccionCustomService.insertar(leccion);
        return modelMapper.map(leccion, LeccionCustomResponseDTO.class);
    }

    @GetMapping
    public List<LeccionCustomResponseDTO> listar() {
        return leccionCustomService.listar().stream()
                .map(leccion -> modelMapper.map(leccion, LeccionCustomResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LeccionCustomResponseDTO listarPorId(@PathVariable Long id) {
        LeccionCustom leccion = leccionCustomService.listarPorId(id);
        return modelMapper.map(leccion, LeccionCustomResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public LeccionCustomResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody LeccionCustomRequestDTO leccionRequestDTO) {
        LeccionCustom leccion = modelMapper.map(leccionRequestDTO, LeccionCustom.class);
        leccion.setId(id);
        leccion = leccionCustomService.actualizar(leccion);
        return modelMapper.map(leccion, LeccionCustomResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        leccionCustomService.eliminar(id);
    }


    @GetMapping("/reporte/dificultad")
    public List<FlashcardReporteDTO> reportePorDificultad() {
        return leccionCustomService.reportePorDificultad();
    }
}
