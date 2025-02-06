package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;
import com.example.Prototipo.services.ParticipantesSesionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/participantessesiones")
@CrossOrigin(origins = "https://localhost:3000")
public class ParticipantesSesionController {

    @Autowired
    private ParticipantesSesionService service;

    // Crear un nuevo participante en la sesión
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ParticipantesSesion participantesSesion, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(participantesSesion));
    }

    // Obtener todas las participaciones en sesiones
    @GetMapping
    public ResponseEntity<List<ParticipantesSesion>> obtenerTodos() {
        List<ParticipantesSesion> participaciones = service.findAll();
        return ResponseEntity.ok(participaciones);
    }

    // Obtener una participación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ParticipantesSesion> obtenerPorId(@PathVariable String id) {  // Cambiado a String
        Optional<ParticipantesSesion> participacion = service.findById(id);
        return participacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una participación por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {  // Cambiado a String
        Optional<ParticipantesSesion> participacion = service.findById(id);
        if (participacion.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participación no encontrada");
        }
    }

    // Modificar una participación
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable String id, @Valid @RequestBody ParticipantesSesion participacionModificada, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        ParticipantesSesion participacionActualizada = service.modificar(id, participacionModificada);

        if (participacionActualizada != null) {
            return ResponseEntity.ok(participacionActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participación no encontrada");
        }
    }
    @GetMapping("/result/{id}")
    public ResponseEntity<Map<String, Object>> obtenerResultado(@PathVariable int id) {
        List<ParticipantesSesion> participantesSesionList = service.findByidSeccion(id);
        Map<String, Object> response = new HashMap<>();
        if (participantesSesionList.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No se encontraron análisis de tiempo real para la sección");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("status", "success");
            response.put("data", participantesSesionList);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/nrc/{nrc}")
    public ResponseEntity<List<ParticipantesSesion>> obtenerParticipantesPorNrc(@PathVariable String nrc) {
        List<ParticipantesSesion> participantes = service.obtenerParticipantesPorNrc(nrc);

        // Si no se encuentran participantes, devolver un mensaje adecuado
        if (participantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(participantes);
    }
}
