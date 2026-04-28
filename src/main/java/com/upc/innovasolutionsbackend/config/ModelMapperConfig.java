package com.upc.innovasolutionsbackend.config;

import com.upc.innovasolutionsbackend.dtos.UsuarioRequestDTO;
import com.upc.innovasolutionsbackend.entidades.Usuario;
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
            mapper.map(UsuarioRequestDTO::getRolId, (dest, v) -> dest.getRol().setId((Long) v));
            mapper.map(UsuarioRequestDTO::getPlanSuscripcionId, (dest, v) -> dest.getPlanSuscripcion().setId((Long) v));
        });

        return modelMapper;
    }
}
