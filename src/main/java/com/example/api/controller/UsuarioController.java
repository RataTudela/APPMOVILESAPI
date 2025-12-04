package com.example.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Usuario;
import com.example.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") 
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario detallesUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setEmail(detallesUsuario.getEmail());
        usuario.setContrasena(detallesUsuario.getContrasena());
        usuario.setTelefono(detallesUsuario.getTelefono());
        usuario.setRegion(detallesUsuario.getRegion());
        usuario.setComuna(detallesUsuario.getComuna());

        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
    
    @GetMapping("/login")
    public Usuario login(
            @RequestParam String email,
            @RequestParam String contrasena
    ) {
        return usuarioRepository.findByEmailAndContrasena(email, contrasena)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

}
