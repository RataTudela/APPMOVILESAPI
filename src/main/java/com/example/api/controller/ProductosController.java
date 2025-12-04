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

import com.example.api.model.Productos;
import com.example.api.repository.ProductosRepository;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") 
public class ProductosController {

    private final ProductosRepository productoRepository;

    public ProductosController(ProductosRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<Productos> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Productos getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @PostMapping
    public Productos createProducto(@RequestBody Productos producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Productos updateProducto(@PathVariable Long id, @RequestBody Productos detallesProducto) {
        Productos producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setTitulo(detallesProducto.getTitulo());
        producto.setDescripcion(detallesProducto.getDescripcion());
        producto.setPrecio(detallesProducto.getPrecio());
        producto.setImagen(detallesProducto.getImagen());
        producto.setQty(detallesProducto.getQty());
        producto.setCategoria(detallesProducto.getCategoria()); 

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}

