package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.dtos.FlashcardReporteDTO;
import com.upc.innovasolutionsbackend.entidades.LeccionCustom;
import com.upc.innovasolutionsbackend.repositorios.LeccionCustomRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeccionCustomService {
    @Autowired
    private LeccionCustomRepositorio leccionCustomRepositorio;

    @Transactional
    public LeccionCustom insertar(LeccionCustom leccion) {
        return leccionCustomRepositorio.save(leccion);
    }

    public List<LeccionCustom> listar() {
        return leccionCustomRepositorio.findAll();
    }

    public LeccionCustom listarPorId(Long id) {
        return leccionCustomRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public LeccionCustom actualizar(LeccionCustom leccion) {
        if (leccionCustomRepositorio.existsById(leccion.getId())) {
            return leccionCustomRepositorio.save(leccion);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        leccionCustomRepositorio.deleteById(id);
    }

//Reporte por la dificultad
    public List<FlashcardReporteDTO> reportePorDificultad() {
        return leccionCustomRepositorio.contarPorDificultad().stream()
                .map(row -> new FlashcardReporteDTO(
                        row[0] != null ? (String) row[0] : "Sin dificultad",
                        (Long) row[1]
                ))
                .collect(Collectors.toList());
    }
}
