package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.Rol;
import com.example.Prototipo.services.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "https://localhost:3000")
public class RolController {

    @Autowired
    private RolService service;

    // Crear un nuevo rol
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Rol rol, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(rol));
    }

    // Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodos() {
        List<Rol> roles = service.findAll();
        return ResponseEntity.ok(roles);
    }

    // Obtener un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Long id) {  // Cambiado a Long
        Optional<Rol> rol = service.findById(id);
        return rol.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un rol por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {  // Cambiado a Long
        Optional<Rol> rol = service.findById(id);
        if (rol.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
    }

    // Modificar un rol
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody Rol rolModificado, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        Rol rolActualizado = service.modificar(id, rolModificado);

        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
    }
}
