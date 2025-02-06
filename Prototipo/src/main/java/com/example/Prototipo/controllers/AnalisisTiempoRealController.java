package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;
import com.example.Prototipo.services.AnalisisTiempoRealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/analisis-tiempo-real")
@CrossOrigin(origins = "https://localhost:3000")
public class AnalisisTiempoRealController {

    @Autowired
    private AnalisisTiempoRealService service;

    // Crear un nuevo análisis de tiempo real
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody AnalisisTiempoReal analisisTiempoReal, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(analisisTiempoReal));
    }

    // Obtener todos los análisis de tiempo real
    @GetMapping
    public ResponseEntity<List<AnalisisTiempoReal>> obtenerTodos() {
        List<AnalisisTiempoReal> analisisList = service.findAll();
        return ResponseEntity.ok(analisisList);
    }

    // Obtener un análisis de tiempo real por su ID
    @GetMapping("/{id}")
    public ResponseEntity<AnalisisTiempoReal> obtenerPorId(@PathVariable String id) {  // Cambiado a String
        Optional<AnalisisTiempoReal> analisisTiempoReal = service.findById(id);
        return analisisTiempoReal.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un análisis de tiempo real por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {  // Cambiado a String
        Optional<AnalisisTiempoReal> analisisTiempoReal = service.findById(id);
        if (analisisTiempoReal.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análisis de tiempo real no encontrado");
        }
    }

    // Modificar un análisis de tiempo real
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable String id, @Valid @RequestBody AnalisisTiempoReal analisisModificado, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        AnalisisTiempoReal analisisActualizado = service.modificar(id, analisisModificado);

        if (analisisActualizado != null) {
            return ResponseEntity.ok(analisisActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análisis de tiempo real no encontrado");
        }
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<Map<String, Object>> obtenerResultado(@PathVariable int id) {
        List<AnalisisTiempoReal> analisisList = service.findByidSeccion(id);
        Map<String, Object> response = new HashMap<>();
        if (analisisList.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No se encontraron análisis de tiempo real para la sección");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("status", "success");
            response.put("data", analisisList);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/nrc/{nrc}")
    public ResponseEntity<List<AnalisisTiempoReal>> obtenerParticipantesPorNrc(@PathVariable String nrc) {
        List<AnalisisTiempoReal> participantes = service.obtenerParticipantesPorNrc(nrc);

        // Si no se encuentran participantes, devolver un mensaje adecuado
        if (participantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(participantes);
    }

}


