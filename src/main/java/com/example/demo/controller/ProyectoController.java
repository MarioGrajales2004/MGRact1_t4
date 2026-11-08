package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST principal.
 * @RestController = @Controller + @ResponseBody: lo que retornan los métodos
 * se escribe directamente en el cuerpo de la respuesta (texto o JSON),
 * sin resolver ninguna vista.
 * @RequestMapping("/api") antepone /api a todas las rutas de la clase.
 */
@RestController
@RequestMapping("/api")
public class ProyectoController {

    /** GET /api/proyecto -> texto plano identificando el proyecto. */
    @GetMapping("/proyecto")
    public String proyecto() {
        return "MGR - Spring Boot Act 1 - Entorno Local OK";
    }

    /** GET /api/estudiante -> objeto JSON (Map serializado por Jackson). */
    @GetMapping("/estudiante")
    public Map<String, Object> estudiante() {
        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("nombre", "Mario Eduardo Grajales Ramírez");
        datos.put("carrera", "Ingeniería en Sistemas Computacionales");
        datos.put("materias", List.of("Programación Web", "Bases de Datos", "Redes"));
        datos.put("estatus", "Activo");
        return datos;
    }
}
