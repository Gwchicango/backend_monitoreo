package com.example.Prototipo.controllers;
import java.util.HashMap;
import java.util.Map;

import com.example.Prototipo.models.entities.Informe;
import com.example.Prototipo.models.entities.Persona;
import com.example.Prototipo.models.entities.Rol;
import com.example.Prototipo.services.InformeService;
import com.example.Prototipo.services.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "https://localhost:3000")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @Autowired
    private InformeService services;

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Persona persona, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(persona));
    }

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodos() {
        List<Persona> personas = service.findAll();
        return ResponseEntity.ok(personas);
    }

    // Obtener una persona por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Long id) {
        Optional<Persona> persona = service.findById(id);
        return persona.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una persona por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Persona> persona = service.findById(id);
        if (persona.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    // Modificar una persona
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody Persona personaModificada, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        Persona personaActualizada = service.modificar(id, personaModificada);

        if (personaActualizada != null) {
            return ResponseEntity.ok(personaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestParam String username, @RequestParam String contraseña) {
    Optional<Persona> persona = service.login(username, contraseña);
    if (persona.isPresent()) {
        // Obtener los detalles de la persona y su rol
        Persona user = persona.get();
        Rol rol = user.getRol();  // Acceder al rol de la persona

        // Crear un mapa con los datos de la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "¡Bienvenido, " + user.getNombre() + "!");
        response.put("idPersona", user.getIdPersona());
        response.put("idInterno", user.getId());
        response.put("username", user.getUsername());
        response.put("nombre", user.getNombre());
        response.put("fechaNacimiento", user.getFechaNacimiento().toString());
        response.put("genero", user.getGenero());
        response.put("email", user.getEmail());
        response.put("rol", rol.getNombreRol());
        response.put("nrc", user.getNrc());
        response.put("idRol", rol.getIdRol());

        // Retornar la respuesta en formato JSON
        return ResponseEntity.ok(response);
    } else {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Credenciales inválidas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}

}
