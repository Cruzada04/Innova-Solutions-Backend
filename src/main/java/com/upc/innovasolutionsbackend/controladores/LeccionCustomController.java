package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.LeccionCustomRequestDTO;
import com.upc.innovasolutionsbackend.dtos.LeccionCustomResponseDTO;
import com.upc.innovasolutionsbackend.entidades.LeccionCustom;
import com.upc.innovasolutionsbackend.servicios.LeccionCustomService;
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
    public LeccionCustomResponseDTO insertar(@RequestBody LeccionCustomRequestDTO leccionRequestDTO) {
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
    public LeccionCustomResponseDTO actualizar(@PathVariable Long id, @RequestBody LeccionCustomRequestDTO leccionRequestDTO) {
        LeccionCustom leccion = modelMapper.map(leccionRequestDTO, LeccionCustom.class);
        leccion.setId(id);
        leccion = leccionCustomService.actualizar(leccion);
        return modelMapper.map(leccion, LeccionCustomResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        leccionCustomService.eliminar(id);
    }
}
