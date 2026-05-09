package com.upc.innovasolutionsbackend.config;

import com.upc.innovasolutionsbackend.dtos.*;
import com.upc.innovasolutionsbackend.entidades.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Mapeo explícito para Usuario
        modelMapper.typeMap(UsuarioRequestDTO.class, Usuario.class).addMappings(mapper -> {
            mapper.map(UsuarioRequestDTO::getRolId, (dest, v) -> {
                if (v != null) {
                    if (dest.getRol() == null) dest.setRol(new Rol());
                    dest.getRol().setId((Long) v);
                }
            });
            mapper.map(UsuarioRequestDTO::getPlanSuscripcionId, (dest, v) -> {
                if (v != null) {
                    if (dest.getPlanSuscripcion() == null) dest.setPlanSuscripcion(new PlanSuscripcion());
                    dest.getPlanSuscripcion().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para Tema
        modelMapper.typeMap(TemaRequestDTO.class, Tema.class).addMappings(mapper -> {
            mapper.map(TemaRequestDTO::getCategoriaId, (dest, v) -> {
                if (v != null) {
                    if (dest.getCategoria() == null) dest.setCategoria(new Categoria());
                    dest.getCategoria().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para ResenaUsuario
        modelMapper.typeMap(ResenaUsuarioRequestDTO.class, ResenaUsuario.class).addMappings(mapper -> {
            mapper.map(ResenaUsuarioRequestDTO::getUsuarioId, (dest, v) -> {
                if (v != null) {
                    if (dest.getUsuario() == null) dest.setUsuario(new Usuario());
                    dest.getUsuario().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para RelacionTutorEstudiante
        modelMapper.typeMap(RelacionTutorEstudianteRequestDTO.class, RelacionTutorEstudiante.class).addMappings(mapper -> {
            mapper.map(RelacionTutorEstudianteRequestDTO::getTutorId, (dest, v) -> {
                if (v != null) {
                    if (dest.getTutor() == null) dest.setTutor(new Usuario());
                    dest.getTutor().setId((Long) v);
                }
            });
            mapper.map(RelacionTutorEstudianteRequestDTO::getEstudianteId, (dest, v) -> {
                if (v != null) {
                    if (dest.getEstudiante() == null) dest.setEstudiante(new Usuario());
                    dest.getEstudiante().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para ProgresoEvaluacion
        modelMapper.typeMap(ProgresoEvaluacionRequestDTO.class, ProgresoEvaluacion.class).addMappings(mapper -> {
            mapper.map(ProgresoEvaluacionRequestDTO::getEstudianteId, (dest, v) -> {
                if (v != null) {
                    if (dest.getEstudiante() == null) dest.setEstudiante(new Usuario());
                    dest.getEstudiante().setId((Long) v);
                }
            });
            mapper.map(ProgresoEvaluacionRequestDTO::getLeccionId, (dest, v) -> {
                if (v != null) {
                    if (dest.getLeccion() == null) dest.setLeccion(new LeccionCustom());
                    dest.getLeccion().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para PerfilAprendizaje
        modelMapper.typeMap(PerfilAprendizajeRequestDTO.class, PerfilAprendizaje.class).addMappings(mapper -> {
            mapper.map(PerfilAprendizajeRequestDTO::getEstudianteId, (dest, v) -> {
                if (v != null) {
                    if (dest.getEstudiante() == null) dest.setEstudiante(new Usuario());
                    dest.getEstudiante().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para OpcionRespuesta
        modelMapper.typeMap(OpcionRespuestaRequestDTO.class, OpcionRespuesta.class).addMappings(mapper -> {
            mapper.map(OpcionRespuestaRequestDTO::getFlashcardId, (dest, v) -> {
                if (v != null) {
                    if (dest.getFlashcard() == null) dest.setFlashcard(new Flashcard());
                    dest.getFlashcard().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para LeccionCustom
        modelMapper.typeMap(LeccionCustomRequestDTO.class, LeccionCustom.class).addMappings(mapper -> {
            mapper.map(LeccionCustomRequestDTO::getCreadorId, (dest, v) -> {
                if (v != null) {
                    if (dest.getCreador() == null) dest.setCreador(new Usuario());
                    dest.getCreador().setId((Long) v);
                }
            });
            mapper.map(LeccionCustomRequestDTO::getEstudianteId, (dest, v) -> {
                if (v != null) {
                    if (dest.getEstudiante() == null) dest.setEstudiante(new Usuario());
                    dest.getEstudiante().setId((Long) v);
                }
            });
            mapper.map(LeccionCustomRequestDTO::getTemaId, (dest, v) -> {
                if (v != null) {
                    if (dest.getTema() == null) dest.setTema(new Tema());
                    dest.getTema().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para Flashcard
        modelMapper.typeMap(FlashcardRequestDTO.class, Flashcard.class).addMappings(mapper -> {
            mapper.map(FlashcardRequestDTO::getLeccionId, (dest, v) -> {
                if (v != null) {
                    if (dest.getLeccion() == null) dest.setLeccion(new LeccionCustom());
                    dest.getLeccion().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para ElementoGuardado
        modelMapper.typeMap(ElementoGuardadoRequestDTO.class, ElementoGuardado.class).addMappings(mapper -> {
            mapper.map(ElementoGuardadoRequestDTO::getUsuarioId, (dest, v) -> {
                if (v != null) {
                    if (dest.getUsuario() == null) dest.setUsuario(new Usuario());
                    dest.getUsuario().setId((Long) v);
                }
            });
        });

        // Mapeo explícito para Configuracion
        modelMapper.typeMap(ConfiguracionRequestDTO.class, Configuracion.class).addMappings(mapper -> {
            mapper.map(ConfiguracionRequestDTO::getUsuarioId, (dest, v) -> {
                if (v != null) {
                    if (dest.getUsuario() == null) dest.setUsuario(new Usuario());
                    dest.getUsuario().setId((Long) v);
                }
            });
        });

        return modelMapper;
    }
}
