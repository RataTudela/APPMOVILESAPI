package com.example.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import com.example.api.model.Categoria;
import com.example.api.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarCategorias() throws Exception {
        Categoria c = new Categoria(1L, "Ropa");

        when(categoriaRepository.findAll())
                .thenReturn(List.of(c));

        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk());
    }

    @Test
    void getCategoriaById() throws Exception {
        Categoria c = new Categoria(1L, "Skate");

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(c));

        mockMvc.perform(get("/api/categorias/1"))
                .andExpect(status().isOk());
    }

    @Test
    void crearCategoria() throws Exception {
        Categoria c = new Categoria(null, "Tabla");

        when(categoriaRepository.save(any(Categoria.class)))
                .thenReturn(c);

        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarCategoria() throws Exception {
        Categoria existente = new Categoria(1L, "Ropa");
        Categoria actualizada = new Categoria(1L, "Ropa Nueva");

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(categoriaRepository.save(any(Categoria.class)))
                .thenReturn(actualizada);

        mockMvc.perform(put("/api/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarCategoria() throws Exception {
        mockMvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isOk());
    }
}

