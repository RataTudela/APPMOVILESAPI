package com.example.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import com.example.api.model.Productos;
import com.example.api.repository.ProductosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductosController.class)
class ProductosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductosRepository productosRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProductos() throws Exception {
        Productos p = new Productos();
        p.setId(1L);
        p.setTitulo("Producto 1");

        when(productosRepository.findAll())
                .thenReturn(List.of(p));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductoById() throws Exception {
        Productos p = new Productos();
        p.setId(1L);
        p.setTitulo("Producto 1");

        when(productosRepository.findById(1L))
                .thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createProducto() throws Exception {
        Productos p = new Productos();
        p.setTitulo("Nuevo Producto");

        when(productosRepository.save(any(Productos.class)))
                .thenReturn(p);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }

    @Test
    void updateProducto() throws Exception {
        Productos existente = new Productos();
        existente.setId(1L);
        existente.setTitulo("Viejo");

        Productos actualizado = new Productos();
        actualizado.setTitulo("Nuevo");

        when(productosRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(productosRepository.save(any(Productos.class)))
                .thenReturn(actualizado);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProducto() throws Exception {
        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());
    }
}

