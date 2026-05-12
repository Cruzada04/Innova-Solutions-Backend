package com.upc.innovasolutionsbackend.security.services;


import com.upc.innovasolutionsbackend.entidades.Usuario;
import com.upc.innovasolutionsbackend.entidades.Rol;
import com.upc.innovasolutionsbackend.security.repositories.RoleRepository;
import com.upc.innovasolutionsbackend.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UsuarioRepositorio userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void save(Usuario usuario) {
        userRepository.save(usuario);
    }

    @Transactional
    public void grabar(Rol rol) {
        roleRepository.save(rol);
    }
}
