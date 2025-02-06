package com.example.Prototipo.controllers;

import com.example.Prototipo.models.entities.Informe;
import com.example.Prototipo.services.InformeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/informes")
@CrossOrigin(origins = "https://localhost:3000")
public class InformeController {

    @Autowired
    private InformeService service;

    // Crear un nuevo informe
    @PostMapping
public ResponseEntity<?> crear(@Valid @RequestBody Informe informe, BindingResult result) {
    if (result.hasErrors()) {
        // Si hay errores en las validaciones, se pueden devolver como respuesta
        StringBuilder errorMessages = new StringBuilder();
        result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
        return ResponseEntity.badRequest().body("{\"message\": \"Error: " + errorMessages.toString() + "\"}");
    }
    service.save(informe);
    return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Informe creado exitosamente\"}");
}

    // Obtener todos los informes
    @GetMapping
    public ResponseEntity<List<Informe>> obtenerTodos() {
        List<Informe> informes = service.findAll();
        return ResponseEntity.ok(informes);
    }

    // Obtener un informe por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Informe> obtenerPorId(@PathVariable Long id) {  // Cambiado a Long
        Optional<Informe> informe = service.findById(id);
        return informe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un informe por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {  // Cambiado a Long
        Optional<Informe> informe = service.findById(id);
        if (informe.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informe no encontrado");
        }
    }

    // Modificar un informe
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody Informe informeModificado, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores en las validaciones, se pueden devolver como respuesta
            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        Informe informeActualizado = service.modificar(id, informeModificado);

        if (informeActualizado != null) {
            return ResponseEntity.ok(informeActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informe no encontrado");
        }
    }

    @GetMapping("/seccion/{id}")
    public List<Informe> getInformesBySeccionTestId(@PathVariable long id) {
        return service.getInformesBySeccionTestId(id);  // Llamada al servicio
    }

    @GetMapping("/nrc/{nrc}")
    public List<Informe> obtenerInformesPorNrc(@PathVariable String nrc) {
        return service.obtenerInformesPorNrc(nrc);
    }

}
