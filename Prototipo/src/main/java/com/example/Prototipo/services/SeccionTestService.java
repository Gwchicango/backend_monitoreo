package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.SeccionTest;

import java.util.List;
import java.util.Optional;

public interface SeccionTestService {

    List<SeccionTest> findAll();
    Optional<SeccionTest> findById(Long id);
    List<SeccionTest> findByPersonaId(Long idPersona);
    SeccionTest save(SeccionTest seccionTest);
    void deleteById(Long id);

    SeccionTest modificar(Long id, SeccionTest seccionTest);
}
