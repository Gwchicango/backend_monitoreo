package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Sesion;

import java.util.List;
import java.util.Optional;

public interface SesionService {

    List<Sesion> findAll();
    Optional<Sesion> findById(Long id);  // Cambiado a Long
    Sesion save(Sesion sesion);
    void deleteById(Long id);  // Cambiado a Long

    Sesion modificar(Long id, Sesion sesion);  // Cambiado a Long
}
