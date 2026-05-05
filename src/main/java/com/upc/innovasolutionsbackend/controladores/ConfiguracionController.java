package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.ConfiguracionRequestDTO;
import com.upc.innovasolutionsbackend.dtos.ConfiguracionResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Configuracion;
import com.upc.innovasolutionsbackend.servicios.ConfiguracionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/configuraciones")
public class ConfiguracionController {
    @Autowired
    private ConfiguracionService configuracionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ConfiguracionResponseDTO insertar(@RequestBody ConfiguracionRequestDTO configuracionRequestDTO) {
        Configuracion configuracion = modelMapper.map(configuracionRequestDTO, Configuracion.class);
        configuracion = configuracionService.insertar(configuracion);
        return modelMapper.map(configuracion, ConfiguracionResponseDTO.class);
    }

    @GetMapping
    public List<ConfiguracionResponseDTO> listar() {
        return configuracionService.listar().stream()
                .map(configuracion -> modelMapper.map(configuracion, ConfiguracionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ConfiguracionResponseDTO listarPorId(@PathVariable Long id) {
        Configuracion configuracion = configuracionService.listarPorId(id);
        return modelMapper.map(configuracion, ConfiguracionResponseDTO.class);
    }

    @PutMapping("/{id}")
    public ConfiguracionResponseDTO actualizar(@PathVariable Long id, @RequestBody ConfiguracionRequestDTO configuracionRequestDTO) {
        Configuracion configuracion = modelMapper.map(configuracionRequestDTO, Configuracion.class);
        configuracion.setId(id);
        configuracion = configuracionService.actualizar(configuracion);
        return modelMapper.map(configuracion, ConfiguracionResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        configuracionService.eliminar(id);
    }
}
