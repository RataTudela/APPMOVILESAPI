package com.example.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import com.example.api.model.Orden;
import com.example.api.model.OrdenItem;
import com.example.api.repository.OrdenItemRepository;
import com.example.api.repository.OrdenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrdenController.class)
class OrdenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdenRepository ordenRepository;

    @MockBean
    private OrdenItemRepository ordenItemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearOrden() throws Exception {
        OrdenItem item = new OrdenItem();
        item.setTitulo("Producto");
        item.setQty(2);
        item.setPrecio(10000);

        Orden orden = new Orden();
        orden.setFecha("2025-12-15");
        orden.setTotal(20000);
        orden.setUsuarioId(1L);
        orden.setNombre("Juan");
        orden.setApellido("Perez");
        orden.setCorreo("juan@test.com");
        orden.setRegion("RM");
        orden.setComuna("Maipú");
        orden.setProductos(List.of(item));

        when(ordenRepository.save(any(Orden.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        when(ordenItemRepository.save(any(OrdenItem.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orden)))
                .andExpect(status().isOk());
    }

    @Test
    void getTodasLasOrdenes() throws Exception {
        Orden o = new Orden();
        o.setId(1L);

        when(ordenRepository.findAll())
                .thenReturn(List.of(o));

        mockMvc.perform(get("/api/ordenes"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrdenesPorUsuario() throws Exception {
        Orden o = new Orden();
        o.setUsuarioId(1L);

        when(ordenRepository.findByUsuarioId(1L))
                .thenReturn(List.of(o));

        mockMvc.perform(get("/api/ordenes/usuario/1"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarEstadoOrden() throws Exception {
        Orden o = new Orden();
        o.setId(1L);
        o.setEstado("pendiente");

        when(ordenRepository.findById(1L))
                .thenReturn(Optional.of(o));

        when(ordenRepository.save(any(Orden.class)))
                .thenReturn(o);

        mockMvc.perform(post("/api/ordenes/1/estado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"enviado\""))
                .andExpect(status().isOk());
    }
}
