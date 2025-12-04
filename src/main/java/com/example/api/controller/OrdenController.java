package com.example.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Orden;
import com.example.api.model.OrdenItem;
import com.example.api.repository.OrdenItemRepository;
import com.example.api.repository.OrdenRepository;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin("*")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepo;

    @Autowired
    private OrdenItemRepository itemRepo;

   @PostMapping
public Orden crear(@RequestBody Orden body) {

    Orden orden = new Orden();
    orden.setFecha(body.getFecha());
    orden.setTotal(body.getTotal());
    orden.setUsuarioId(body.getUsuarioId());

    orden.setNombre(body.getNombre());
    orden.setApellido(body.getApellido());
    orden.setCorreo(body.getCorreo());
    orden.setCalle(body.getCalle());
    orden.setDepartamento(body.getDepartamento());
    orden.setRegion(body.getRegion());
    orden.setComuna(body.getComuna());
    orden.setIndicaciones(body.getIndicaciones());

    orden.setEstado(body.getEstado() != null ? body.getEstado() : "pendiente");

    Orden saved = ordenRepo.save(orden);

        List<OrdenItem> items = new ArrayList<>();
        for (OrdenItem p : body.getProductos()) {
            OrdenItem item = new OrdenItem();
            item.setTitulo(p.getTitulo());
            item.setQty(p.getQty());
            item.setPrecio(p.getPrecio());
            item.setOrden(saved);
            itemRepo.save(item);
            items.add(item);
        }

        saved.setProductos(items);

        return saved;
    }

    @GetMapping
    public List<Orden> todas() {
        return ordenRepo.findAll();
    }

    @GetMapping("/usuario/{id}")
    public List<Orden> porUsuario(@PathVariable Long id) {
        return ordenRepo.findByUsuarioId(id);
    }

    @PostMapping("/{id}/estado")
    public Orden actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
    Orden orden = ordenRepo.findById(id).orElseThrow();
    orden.setEstado(nuevoEstado.replace("\"", ""));
    return ordenRepo.save(orden);
    }

}
