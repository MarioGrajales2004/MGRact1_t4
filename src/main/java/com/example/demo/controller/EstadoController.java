package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Segundo controlador REST: estado del servidor. */
@RestController
public class EstadoController {

    /** GET /estado -> JSON con el estado y la fecha/hora del sistema. */
    @GetMapping("/estado")
    public Map<String, String> estado() {
        return Map.of(
                "estado", "Servidor en ejecución",
                "fechaHora", LocalDateTime.now().toString());
    }
}
