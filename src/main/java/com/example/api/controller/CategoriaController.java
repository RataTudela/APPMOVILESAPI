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
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Categoria;
import com.example.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

     @GetMapping("/{id}")
    public Categoria gCategoriaById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable Long id, @RequestBody Categoria detalleCategoria) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoria.setNombre(detalleCategoria.getNombre());
        return categoriaRepository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
    }
}

