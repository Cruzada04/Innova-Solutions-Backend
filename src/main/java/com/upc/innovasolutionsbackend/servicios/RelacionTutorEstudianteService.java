package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.RelacionTutorEstudiante;
import com.upc.innovasolutionsbackend.repositorios.RelacionTutorEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RelacionTutorEstudianteService {
    @Autowired
    private RelacionTutorEstudianteRepositorio relacionTutorEstudianteRepositorio;

    @Transactional
    public RelacionTutorEstudiante insertar(RelacionTutorEstudiante relacion) {
        return relacionTutorEstudianteRepositorio.save(relacion);
    }

    public List<RelacionTutorEstudiante> listar() {
        return relacionTutorEstudianteRepositorio.findAll();
    }

    public RelacionTutorEstudiante listarPorId(Long id) {
        return relacionTutorEstudianteRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public RelacionTutorEstudiante actualizar(RelacionTutorEstudiante relacion) {
        if (relacionTutorEstudianteRepositorio.existsById(relacion.getId())) {
            return relacionTutorEstudianteRepositorio.save(relacion);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        relacionTutorEstudianteRepositorio.deleteById(id);
    }
}
