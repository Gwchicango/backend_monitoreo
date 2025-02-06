package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, Long> {

    // Método personalizado para buscar por username y contraseña
    Optional<Persona> findByUsernameAndContraseña(String username, String contraseña);

    // Método personalizado para buscar por NRC
    List<Persona> findByNrc(String nrc);

}
