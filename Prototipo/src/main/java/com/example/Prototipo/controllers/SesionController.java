package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.Sesion;
import com.example.Prototipo.services.SesionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sesiones")
@CrossOrigin(origins = "https://localhost:3000")
public class SesionController {

    @Autowired
    private SesionService service;

    // Crear una nueva sesión
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Sesion sesion, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(sesion));
    }

    // Obtener todas las sesiones
    @GetMapping
    public ResponseEntity<List<Sesion>> obtenerTodos() {
        List<Sesion> sesiones = service.findAll();
        return ResponseEntity.ok(sesiones);
    }

    // Obtener una sesión por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Sesion> obtenerPorId(@PathVariable Long id) {  // Cambiado a Long
        Optional<Sesion> sesion = service.findById(id);
        return sesion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una sesión por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {  // Cambiado a Long
        Optional<Sesion> sesion = service.findById(id);
        if (sesion.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sesión no encontrada");
        }
    }

    // Modificar una sesión
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody Sesion sesionModificada, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        Sesion sesionActualizada = service.modificar(id, sesionModificada);

        if (sesionActualizada != null) {
            return ResponseEntity.ok(sesionActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sesión no encontrada");
        }
    }
}