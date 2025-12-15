package com.example.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.example.api.model.Reportes;
import com.example.api.repository.ReportesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReportesController.class)
class ReportesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportesRepository reportesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllReportes() throws Exception {
        Reportes r = new Reportes(
                1L,
                "Juan",
                "juan@test.com",
                "Texto",
                "2025-12-15"
        );

        when(reportesRepository.findAll())
                .thenReturn(List.of(r));

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk());
    }

    @Test
    void createReporte() throws Exception {
        Reportes r = new Reportes(
                null,
                "Pedro",
                "pedro@test.com",
                "Hola",
                "2025-12-15"
        );

        when(reportesRepository.save(any(Reportes.class)))
                .thenReturn(r);

        mockMvc.perform(post("/api/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(r)))
                .andExpect(status().isOk());
    }
}
