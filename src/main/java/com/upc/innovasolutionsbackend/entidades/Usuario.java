package com.upc.innovasolutionsbackend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCompleto;
    private String correoElectronico;
    private String contrasena;
    private String metodoRegistro;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private PlanSuscripcion planSuscripcion;

    @OneToMany(mappedBy = "usuario")
    private List<ElementoGuardado> elementosGuardados;

    @OneToMany(mappedBy = "usuario")
    private List<ResenaUsuario> resenas;

    @OneToMany(mappedBy = "tutor")
    private List<RelacionTutorEstudiante> estudiantesAsignados;

    @OneToMany(mappedBy = "estudiante")
    private List<RelacionTutorEstudiante> tutoresAsignados;

    @OneToOne(mappedBy = "estudiante")
    private PerfilAprendizaje perfilAprendizaje;

    @OneToOne(mappedBy = "usuario")
    private Configuracion configuracion;

    @OneToMany(mappedBy = "creador")
    private List<LeccionCustom> leccionesCreadas;

    @OneToMany(mappedBy = "estudiante")
    private List<LeccionCustom> leccionesAsignadas;

    @OneToMany(mappedBy = "estudiante")
    private List<ProgresoEvaluacion> progresosEvaluacion;
}
