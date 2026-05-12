package com.upc.innovasolutionsbackend.servicios;

import com.upc.innovasolutionsbackend.entidades.PlanSuscripcion;
import com.upc.innovasolutionsbackend.repositorios.PlanSuscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanSuscripcionService {
    @Autowired
    private PlanSuscripcionRepositorio planSuscripcionRepositorio;

    @Transactional
    public PlanSuscripcion insertar(PlanSuscripcion plan) {
        return planSuscripcionRepositorio.save(plan);
    }

    public List<PlanSuscripcion> listar() {
        return planSuscripcionRepositorio.findAll();
    }

    public PlanSuscripcion listarPorId(Long id) {
        return planSuscripcionRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public PlanSuscripcion actualizar(PlanSuscripcion plan) {
        if (planSuscripcionRepositorio.existsById(plan.getId())) {
            return planSuscripcionRepositorio.save(plan);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        planSuscripcionRepositorio.deleteById(id);
    }
}
