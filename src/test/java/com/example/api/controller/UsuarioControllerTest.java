package com.example.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import com.example.api.model.SolicitudActualizarClave;
import com.example.api.model.Usuario;
import com.example.api.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsuarios() throws Exception {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setNombre("Juan");

        when(usuarioRepository.findAll()).thenReturn(List.of(u));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk());
    }

    @Test
    void getUsuarioById() throws Exception {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setNombre("Pedro");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(u));

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createUsuario() throws Exception {
        Usuario u = new Usuario();
        u.setNombre("Maria");

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(u);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        Usuario u = new Usuario();
        u.setEmail("test@test.com");

        when(usuarioRepository.findByEmailAndContrasena("test@test.com", "1234"))
                .thenReturn(Optional.of(u));

        mockMvc.perform(get("/api/usuarios/login")
                        .param("email", "test@test.com")
                        .param("contrasena", "1234"))
                .andExpect(status().isOk());
    }

    @Test
    void recuperarContrasena() throws Exception {
        Usuario u = new Usuario();
        u.setEmail("correo@test.com");

        SolicitudActualizarClave req =
                new SolicitudActualizarClave("correo@test.com", "nueva123");

        when(usuarioRepository.findByEmail("correo@test.com"))
                .thenReturn(Optional.of(u));

        mockMvc.perform(post("/api/usuarios/recuperar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }
}
