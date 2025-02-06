package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> findAll();
    Optional<Rol> findById(Long id);  // Cambiado a Long
    Rol save(Rol rol);
    void deleteById(Long id);  // Cambiado a Long

    Rol modificar(Long id, Rol rol);  // Cambiado a Long
}
