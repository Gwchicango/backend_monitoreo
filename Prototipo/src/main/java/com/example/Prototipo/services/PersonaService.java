package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<Persona> findAll();
    Optional<Persona> findById(Long id);
    Persona save(Persona persona);
    void deleteById(Long id);
    Persona modificar(Long id, Persona persona);

    Optional<Persona> login(String username, String password);
}
