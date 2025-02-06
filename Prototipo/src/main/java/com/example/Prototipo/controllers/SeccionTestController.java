package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.SeccionTest;
import com.example.Prototipo.services.SeccionTestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/secciones")
public class SeccionTestController {

    @Autowired
    private SeccionTestService service;

  @PostMapping
public ResponseEntity<?> crear(@Valid @RequestBody SeccionTest seccionTest, BindingResult result) {
    if (result.hasErrors()) {
        // Si hay errores en las validaciones, se pueden devolver como respuesta
        StringBuilder errorMessages = new StringBuilder();
        result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
        return ResponseEntity.badRequest().body(errorMessages.toString());
    }
    SeccionTest nuevaSeccion = service.save(seccionTest);
    Map<String, Long> response = new HashMap<>();
    response.put("id", nuevaSeccion.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

    // Obtener todas las secciones
    @GetMapping
    public ResponseEntity<List<SeccionTest>> obtenerTodos() {
        List<SeccionTest> secciones = service.findAll();
        return ResponseEntity.ok(secciones);
    }

    // Obtener una sección por su ID
    @GetMapping("/{id}")
    public ResponseEntity<SeccionTest> obtenerPorId(@PathVariable Long id) {
        Optional<SeccionTest> seccionTest = service.findById(id);
        return seccionTest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una sección por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<SeccionTest> seccionTest = service.findById(id);
        if (seccionTest.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sección no encontrada");
        }
    }

    // Modificar una sección
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody SeccionTest seccionModificada, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        SeccionTest seccionActualizada = service.modificar(id, seccionModificada);

        if (seccionActualizada != null) {
            return ResponseEntity.ok(seccionActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sección no encontrada");
        }
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorTest(@PathVariable Long id) {
        List<SeccionTest> seccionTests = service.findByPersonaId(id);
        Map<String, Object> response = new HashMap<>();
        if (!seccionTests.isEmpty()) {
            response.put("status", "success");
            response.put("data", seccionTests);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Sección no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
