package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.ProgresoEvaluacionRequestDTO;
import com.upc.innovasolutionsbackend.dtos.ProgresoEvaluacionResponseDTO;
import com.upc.innovasolutionsbackend.dtos.ProgresoReporteDTO;
import com.upc.innovasolutionsbackend.entidades.ProgresoEvaluacion;
import com.upc.innovasolutionsbackend.servicios.ProgresoEvaluacionService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progresos-evaluacion")
public class ProgresoEvaluacionController {
    @Autowired
    private ProgresoEvaluacionService progresoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO al momento de la creación
    public ProgresoEvaluacionResponseDTO insertar(@Valid @RequestBody ProgresoEvaluacionRequestDTO progresoRequestDTO) {
        ProgresoEvaluacion progreso = modelMapper.map(progresoRequestDTO, ProgresoEvaluacion.class);
        progreso = progresoService.insertar(progreso);
        return modelMapper.map(progreso, ProgresoEvaluacionResponseDTO.class);
    }

    @GetMapping
    public List<ProgresoEvaluacionResponseDTO> listar() {
        return progresoService.listar().stream()
                .map(progreso -> modelMapper.map(progreso, ProgresoEvaluacionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgresoEvaluacionResponseDTO listarPorId(@PathVariable Long id) {
        ProgresoEvaluacion progreso = progresoService.listarPorId(id);
        return modelMapper.map(progreso, ProgresoEvaluacionResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public ProgresoEvaluacionResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ProgresoEvaluacionRequestDTO progresoRequestDTO) {
        ProgresoEvaluacion progreso = modelMapper.map(progresoRequestDTO, ProgresoEvaluacion.class);
        progreso.setId(id);
        progreso = progresoService.actualizar(progreso);
        return modelMapper.map(progreso, ProgresoEvaluacionResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        progresoService.eliminar(id);
    }


    @GetMapping("/reporte/pormes")
    public List<ProgresoReporteDTO> reportePorMes() {
        return progresoService.reportePorMes();
    }
}
