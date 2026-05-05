package com.upc.innovasolutionsbackend.controladores;

import com.upc.innovasolutionsbackend.dtos.CategoriaRequestDTO;
import com.upc.innovasolutionsbackend.dtos.CategoriaResponseDTO;
import com.upc.innovasolutionsbackend.entidades.Categoria;
import com.upc.innovasolutionsbackend.servicios.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public CategoriaResponseDTO insertar(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = modelMapper.map(categoriaRequestDTO, Categoria.class);
        categoria = categoriaService.insertar(categoria);
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listar().stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO listarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.listarPorId(id);
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO actualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = modelMapper.map(categoriaRequestDTO, Categoria.class);
        categoria.setId(id);
        categoria = categoriaService.actualizar(categoria);
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
