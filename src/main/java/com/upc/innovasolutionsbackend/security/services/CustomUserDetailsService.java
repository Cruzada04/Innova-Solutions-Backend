package com.upc.innovasolutionsbackend.security.services;
import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.repositorios.UsuarioRepositorio;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Busca al usuario en la BD por su username.
 * Si no existe → lanza excepción.
 * Convierte sus roles en GrantedAuthority.
 * Devuelve un UserDetails que Spring Security usará para:
 * - Verificar la contraseña al hacer login.
 * - Saber qué roles/authorities tiene para autorización (@PreAuthorize, .hasRole(), etc.).
 * ------------------
 * Su propósito principal es cargar los detalles de un usuario a partir de un identificador,
 * que generalmente es el nombre de usuario (username).
 * Es usado por JwtRequestFilter
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepositorio userRepository;

    public CustomUserDetailsService(UsuarioRepositorio userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // DEBUG: ver qué valores tiene el usuario
        System.out.println("=== CUSTOM_USER_DETAILS ===");
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Rol via getRol(): " + (usuario.getRol() != null ? usuario.getRol().getNombre() : "NULL"));
        System.out.println("Roles via getRoles(): " + (usuario.getRoles() != null
                ? usuario.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet())
                : "NULL"));

        // PRIORIDAD 1: usar la columna directa rol_id (usuario.getRol())
        Set<Rol> rolesDelUsuario;
        if (usuario.getRol() != null) {
            rolesDelUsuario = Collections.singleton(usuario.getRol());
            System.out.println("Usando getRol() como fuente de autoridad");
        } else if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            rolesDelUsuario = usuario.getRoles();
            System.out.println("Usando getRoles() (ManyToMany) como fuente de autoridad");
        } else {
            rolesDelUsuario = Collections.emptySet();
            System.out.println("NO SE ENCONTRARON ROLES");
        }

        Set<GrantedAuthority> authorities = rolesDelUsuario.stream()
                .map(role -> {
                    String roleName = role.getNombre().toUpperCase();
                    if (!roleName.startsWith("ROLE_")) {
                        roleName = "ROLE_" + roleName;
                    }
                    System.out.println("Autoridad generada: " + roleName);
                    return new SimpleGrantedAuthority(roleName);
                })
                .collect(Collectors.toSet());

        System.out.println("Autoridades finales: " + authorities);
        System.out.println("=== FIN CUSTOM_USER_DETAILS ===");

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getContrasena())
                .authorities(authorities)
                .build();
    }
}
