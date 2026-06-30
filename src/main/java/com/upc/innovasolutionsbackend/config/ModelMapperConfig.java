package com.upc.innovasolutionsbackend.config;

import com.upc.innovasolutionsbackend.dtos.*;
import com.upc.innovasolutionsbackend.entidades.*;
import org.modelmapper.Converter;
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

        // Converters
        Converter<Long, Rol> idToRol = ctx -> {
            if (ctx.getSource() == null) return null;
            Rol entity = new Rol();
            entity.setId(ctx.getSource());
            return entity;
        };
        Converter<Long, PlanSuscripcion> idToPlanSuscripcion = ctx -> {
            if (ctx.getSource() == null) return null;
            PlanSuscripcion entity = new PlanSuscripcion();
            entity.setId(ctx.getSource());
            return entity;
        };
        Converter<Long, Categoria> idToCategoria = ctx -> {
            if (ctx.getSource() == null) return null;
            Categoria entity = new Categoria();
            entity.setId(ctx.getSource());
            return entity;
        };
        Converter<Long, Usuario> idToUsuario = ctx -> {
            if (ctx.getSource() == null) return null;
            Usuario entity = new Usuario();
            entity.setId(ctx.getSource());
            return entity;
        };
        Converter<Long, Tema> idToTema = ctx -> {
            if (ctx.getSource() == null) return null;
            Tema entity = new Tema();
            entity.setId(ctx.getSource());
            return entity;
        };
        Converter<Long, LeccionCustom> idToLeccionCustom = ctx -> {
            if (ctx.getSource() == null) return null;
            LeccionCustom entity = new LeccionCustom();
            entity.setId(ctx.getSource());
            return entity;
        };
        Converter<Long, Flashcard> idToFlashcard = ctx -> {
            if (ctx.getSource() == null) return null;
            Flashcard entity = new Flashcard();
            entity.setId(ctx.getSource());
            return entity;
        };

        // Mapeo explícito para Usuario
        modelMapper.typeMap(UsuarioRequestDTO.class, Usuario.class).addMappings(mapper -> {
            mapper.using(idToRol).map(UsuarioRequestDTO::getRolId, Usuario::setRol);
            mapper.using(idToPlanSuscripcion).map(UsuarioRequestDTO::getPlanSuscripcionId, Usuario::setPlanSuscripcion);
        });

        modelMapper.typeMap(Usuario.class, UsuarioResponseDTO.class).addMappings(mapper ->
                mapper.map(src -> src.getCreadoPor() != null ? src.getCreadoPor().getId() : null,
                        UsuarioResponseDTO::setCreadoPorId)
        );

        // Mapeo explícito para Tema
        modelMapper.typeMap(TemaRequestDTO.class, Tema.class).addMappings(mapper -> {
            mapper.using(idToCategoria).map(TemaRequestDTO::getCategoriaId, Tema::setCategoria);
        });

        // Mapeo explícito para ResenaUsuario
        modelMapper.typeMap(ResenaUsuarioRequestDTO.class, ResenaUsuario.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(ResenaUsuarioRequestDTO::getUsuarioId, ResenaUsuario::setUsuario);
        });

        // Mapeo explícito para RelacionTutorEstudiante
        modelMapper.typeMap(RelacionTutorEstudianteRequestDTO.class, RelacionTutorEstudiante.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(RelacionTutorEstudianteRequestDTO::getTutorId, RelacionTutorEstudiante::setTutor);
            mapper.using(idToUsuario).map(RelacionTutorEstudianteRequestDTO::getEstudianteId, RelacionTutorEstudiante::setEstudiante);
        });

        // Mapeo explícito para ProgresoEvaluacion
        modelMapper.typeMap(ProgresoEvaluacionRequestDTO.class, ProgresoEvaluacion.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(ProgresoEvaluacionRequestDTO::getEstudianteId, ProgresoEvaluacion::setEstudiante);
            mapper.using(idToLeccionCustom).map(ProgresoEvaluacionRequestDTO::getLeccionId, ProgresoEvaluacion::setLeccion);
        });

        // Mapeo explícito para PerfilAprendizaje
        modelMapper.typeMap(PerfilAprendizajeRequestDTO.class, PerfilAprendizaje.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(PerfilAprendizajeRequestDTO::getEstudianteId, PerfilAprendizaje::setEstudiante);
        });

        // Mapeo explícito para OpcionRespuesta
        modelMapper.typeMap(OpcionRespuestaRequestDTO.class, OpcionRespuesta.class).addMappings(mapper -> {
            mapper.using(idToFlashcard).map(OpcionRespuestaRequestDTO::getFlashcardId, OpcionRespuesta::setFlashcard);
        });

        // Mapeo explícito para LeccionCustom
        modelMapper.typeMap(LeccionCustomRequestDTO.class, LeccionCustom.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(LeccionCustomRequestDTO::getCreadorId, LeccionCustom::setCreador);
            mapper.using(idToUsuario).map(LeccionCustomRequestDTO::getEstudianteId, LeccionCustom::setEstudiante);
            mapper.using(idToTema).map(LeccionCustomRequestDTO::getTemaId, LeccionCustom::setTema);
        });

        // Mapeo explícito para Flashcard
        modelMapper.typeMap(FlashcardRequestDTO.class, Flashcard.class).addMappings(mapper -> {
            mapper.using(idToLeccionCustom).map(FlashcardRequestDTO::getLeccionId, Flashcard::setLeccion);
        });

        // Mapeo explícito para ElementoGuardado
        modelMapper.typeMap(ElementoGuardadoRequestDTO.class, ElementoGuardado.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(ElementoGuardadoRequestDTO::getUsuarioId, ElementoGuardado::setUsuario);
        });

        // Mapeo explícito para Configuracion
        modelMapper.typeMap(ConfiguracionRequestDTO.class, Configuracion.class).addMappings(mapper -> {
            mapper.using(idToUsuario).map(ConfiguracionRequestDTO::getUsuarioId, Configuracion::setUsuario);
        });

        return modelMapper;
    }
}
