package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.Conferencia;
import com.example.Prototipo.services.ConferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conferencias")
@CrossOrigin(origins = "https://localhost:3000")
public class ConferenciaController {

    @Autowired
    private ConferenciaService service;

    // Crear una nueva conferencia
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Conferencia conferencia, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(conferencia));
    }

    // Obtener todas las conferencias
    @GetMapping
    public ResponseEntity<List<Conferencia>> obtenerTodos() {
        List<Conferencia> conferencias = service.findAll();
        return ResponseEntity.ok(conferencias);
    }

    // Obtener una conferencia por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Conferencia> obtenerPorId(@PathVariable Long id) {  // Cambiado a Long
        Optional<Conferencia> conferencia = service.findById(id);
        return conferencia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una conferencia por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {  // Cambiado a Long
        Optional<Conferencia> conferencia = service.findById(id);
        if (conferencia.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conferencia no encontrada");
        }
    }

    // Modificar una conferencia
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody Conferencia conferenciaModificada, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        Conferencia conferenciaActualizada = service.modificar(id, conferenciaModificada);

        if (conferenciaActualizada != null) {
            return ResponseEntity.ok(conferenciaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conferencia no encontrada");
        }
    }
}
