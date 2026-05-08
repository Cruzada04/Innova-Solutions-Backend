package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.PlanSuscripcionRequestDTO;
import com.upc.innovasolutionsbackend.dtos.PlanSuscripcionResponseDTO;
import com.upc.innovasolutionsbackend.entidades.PlanSuscripcion;
import com.upc.innovasolutionsbackend.servicios.PlanSuscripcionService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/planes-suscripcion")
public class PlanSuscripcionController {
    @Autowired
    private PlanSuscripcionService planService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO al momento de la creación
    public PlanSuscripcionResponseDTO insertar(@Valid @RequestBody PlanSuscripcionRequestDTO planRequestDTO) {
        PlanSuscripcion plan = modelMapper.map(planRequestDTO, PlanSuscripcion.class);
        plan = planService.insertar(plan);
        return modelMapper.map(plan, PlanSuscripcionResponseDTO.class);
    }

    @GetMapping
    public List<PlanSuscripcionResponseDTO> listar() {
        return planService.listar().stream()
                .map(plan -> modelMapper.map(plan, PlanSuscripcionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlanSuscripcionResponseDTO listarPorId(@PathVariable Long id) {
        PlanSuscripcion plan = planService.listarPorId(id);
        return modelMapper.map(plan, PlanSuscripcionResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public PlanSuscripcionResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody PlanSuscripcionRequestDTO planRequestDTO) {
        PlanSuscripcion plan = modelMapper.map(planRequestDTO, PlanSuscripcion.class);
        plan.setId(id);
        plan = planService.actualizar(plan);
        return modelMapper.map(plan, PlanSuscripcionResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        planService.eliminar(id);
    }
}
