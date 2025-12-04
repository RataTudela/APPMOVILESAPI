package com.example.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaApi {
    private boolean exito;
    private String mensaje;
}
