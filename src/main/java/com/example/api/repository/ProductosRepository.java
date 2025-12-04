package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos,Long>{

    
}
