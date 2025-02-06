package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.SeccionTest;
import com.example.Prototipo.repositories.SeccionTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionTestServiceImpl implements SeccionTestService {

    @Autowired
    private SeccionTestRepository repository;

    @Override
    public List<SeccionTest> findAll() {
        return (List<SeccionTest>) repository.findAll();
    }

    @Override
    public Optional<SeccionTest> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<SeccionTest> findByPersonaId(Long idPersona) {
        return repository.findByPersonaId(idPersona.intValue());
    }

    @Override
    public SeccionTest save(SeccionTest seccionTest) {
        return repository.save(seccionTest);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public SeccionTest modificar(Long id, SeccionTest seccionTest) {
        Optional<SeccionTest> seccionExistente = repository.findById(id);

        if (seccionExistente.isPresent()) {
            // Actualizar los campos de la sección encontrada con los nuevos valores
            SeccionTest seccionActualizada = seccionExistente.get();
            seccionActualizada.setFechaHoraInicio(seccionTest.getFechaHoraInicio());
            seccionActualizada.setFechaHoraFin(seccionTest.getFechaHoraFin());

            // Guardar la sección modificada
            return repository.save(seccionActualizada);
        } else {
            return null;  // O lanzar una excepción si la sección no se encuentra
        }
    }
}
