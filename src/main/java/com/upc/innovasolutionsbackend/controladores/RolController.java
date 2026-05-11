package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.RolRequestDTO;
import com.upc.innovasolutionsbackend.dtos.RolResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.servicios.RolService;
import jakarta.validation.Valid; // Importación necesaria para activar la validación
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    // Se agrega @Valid para validar el DTO al momento de la creación
    public RolResponseDTO insertar(@Valid @RequestBody RolRequestDTO rolRequestDTO) {
        Rol rol = modelMapper.map(rolRequestDTO, Rol.class);
        rol = rolService.insertar(rol);
        return modelMapper.map(rol, RolResponseDTO.class);
    }

    @GetMapping
    public List<RolResponseDTO> listar() {
        return rolService.listar().stream()
                .map(rol -> modelMapper.map(rol, RolResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RolResponseDTO listarPorId(@PathVariable Long id) {
        Rol rol = rolService.listarPorId(id);
        return modelMapper.map(rol, RolResponseDTO.class);
    }

    @PutMapping("/{id}")
    // Se agrega @Valid para asegurar que los datos actualizados también sean correctos
    public RolResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody RolRequestDTO rolRequestDTO) {
        Rol rol = modelMapper.map(rolRequestDTO, Rol.class);
        rol.setId(id);
        rol = rolService.actualizar(rol);
        return modelMapper.map(rol, RolResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
    }
}
