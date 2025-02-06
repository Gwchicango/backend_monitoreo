package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Persona;
import com.example.Prototipo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository repository;

    @Override
    public List<Persona> findAll() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Persona save(Persona persona) {
        return repository.save(persona);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Persona modificar(Long id, Persona persona) {
        Optional<Persona> personaExistente = repository.findById(id);

        if (personaExistente.isPresent()) {
            Persona personaActualizada = personaExistente.get();
            personaActualizada.setNombre(persona.getNombre());
            personaActualizada.setFechaNacimiento(persona.getFechaNacimiento());
            personaActualizada.setGenero(persona.getGenero());
            personaActualizada.setEmail(persona.getEmail());
            personaActualizada.setContraseña(persona.getContraseña());
            return repository.save(personaActualizada);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Persona> login(String username, String contraseña) {
        return repository.findByUsernameAndContraseña(username, contraseña);
    }
}
