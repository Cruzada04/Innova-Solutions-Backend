package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.OpcionRespuestaRequestDTO;
import com.upc.innovasolutionsbackend.dtos.OpcionRespuestaResponseDTO;
import com.upc.innovasolutionsbackend.entidades.OpcionRespuesta;
import com.upc.innovasolutionsbackend.servicios.OpcionRespuestaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/opciones-respuesta")
public class OpcionRespuestaController {
    @Autowired
    private OpcionRespuestaService opcionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public OpcionRespuestaResponseDTO insertar(@RequestBody OpcionRespuestaRequestDTO opcionRequestDTO) {
        OpcionRespuesta opcion = modelMapper.map(opcionRequestDTO, OpcionRespuesta.class);
        opcion = opcionService.insertar(opcion);
        return modelMapper.map(opcion, OpcionRespuestaResponseDTO.class);
    }

    @GetMapping
    public List<OpcionRespuestaResponseDTO> listar() {
        return opcionService.listar().stream()
                .map(opcion -> modelMapper.map(opcion, OpcionRespuestaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OpcionRespuestaResponseDTO listarPorId(@PathVariable Long id) {
        OpcionRespuesta opcion = opcionService.listarPorId(id);
        return modelMapper.map(opcion, OpcionRespuestaResponseDTO.class);
    }

    @PutMapping("/{id}")
    public OpcionRespuestaResponseDTO actualizar(@PathVariable Long id, @RequestBody OpcionRespuestaRequestDTO opcionRequestDTO) {
        OpcionRespuesta opcion = modelMapper.map(opcionRequestDTO, OpcionRespuesta.class);
        opcion.setId(id);
        opcion = opcionService.actualizar(opcion);
        return modelMapper.map(opcion, OpcionRespuestaResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        opcionService.eliminar(id);
    }
}
