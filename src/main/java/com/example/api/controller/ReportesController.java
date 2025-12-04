package com.example.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Reportes;
import com.example.api.repository.ReportesRepository;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportesController {

    private final ReportesRepository reporteRepository;

    public ReportesController(ReportesRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @GetMapping
    public List<Reportes> getAllReportes() {
        return reporteRepository.findAll();
    }

    @PostMapping
    public Reportes createReporte(@RequestBody Reportes reporte) {
        return reporteRepository.save(reporte);
    }
}
