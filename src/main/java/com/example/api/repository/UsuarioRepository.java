package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailAndContrasena(String email, String contrasena);
    boolean existsByEmail(String email);
}
