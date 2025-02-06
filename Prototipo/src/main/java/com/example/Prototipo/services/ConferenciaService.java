package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Conferencia;

import java.util.List;
import java.util.Optional;

public interface ConferenciaService {

    List<Conferencia> findAll();
    Optional<Conferencia> findById(Long id);  // Cambiado a Long
    Conferencia save(Conferencia conferencia);
    void deleteById(Long id);  // Cambiado a Long

    Conferencia modificar(Long id, Conferencia conferencia);  // Cambiado a Long
}
