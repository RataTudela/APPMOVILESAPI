package com.example.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private int total;
    private String nombre;
    private String apellido;
    private String correo;
    private String calle;
    private String departamento;
    private String region;
    private String comuna;
    private String indicaciones;
    private Long usuarioId; 
    private String estado = "pendiente";
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrdenItem> productos; 
}
