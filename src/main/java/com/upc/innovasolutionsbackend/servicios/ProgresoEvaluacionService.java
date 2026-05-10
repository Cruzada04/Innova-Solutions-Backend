package com.upc.innovasolutionsbackend.servicios;


import com.upc.innovasolutionsbackend.dtos.ProgresoReporteDTO;
import com.upc.innovasolutionsbackend.entidades.ProgresoEvaluacion;
import com.upc.innovasolutionsbackend.repositorios.ProgresoEvaluacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgresoEvaluacionService {
    @Autowired
    private ProgresoEvaluacionRepositorio progresoEvaluacionRepositorio;

    @Transactional
    public ProgresoEvaluacion insertar(ProgresoEvaluacion progreso) {
        return progresoEvaluacionRepositorio.save(progreso);
    }

    public List<ProgresoEvaluacion> listar() {
        return progresoEvaluacionRepositorio.findAll();
    }

    public ProgresoEvaluacion listarPorId(Long id) {
        return progresoEvaluacionRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public ProgresoEvaluacion actualizar(ProgresoEvaluacion progreso) {
        if (progresoEvaluacionRepositorio.existsById(progreso.getId())) {
            return progresoEvaluacionRepositorio.save(progreso);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        progresoEvaluacionRepositorio.deleteById(id);
    }



    public List<ProgresoReporteDTO> reportePorMes() {
        return progresoEvaluacionRepositorio.contarPorMes().stream()
                .map(row -> new ProgresoReporteDTO(
                        (String) row[0],
                        (Long) row[1],
                        (Double) row[2],
                        (Long) row[3]
                ))
                .collect(Collectors.toList());
    }

}
