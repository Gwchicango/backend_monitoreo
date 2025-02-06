package com.example.Prototipo.services;


import com.example.Prototipo.models.entities.Sesion;
import com.example.Prototipo.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionServiceImpl implements SesionService {

    @Autowired
    private SesionRepository repository;

    @Override
    public List<Sesion> findAll() {
        return (List<Sesion>) repository.findAll();
    }

    @Override
    public Optional<Sesion> findById(Long id) {  // Cambiado a Long
        return repository.findById(id);
    }

    @Override
    public Sesion save(Sesion sesion) {
        return repository.save(sesion);
    }

    @Override
    public void deleteById(Long id) {  // Cambiado a Long
        repository.deleteById(id);
    }

    @Override
    public Sesion modificar(Long id, Sesion sesion) {
        Optional<Sesion> sesionExistente = repository.findById(id);

        if (sesionExistente.isPresent()) {
            // Actualizar los campos de la sesión encontrada con los nuevos valores
            Sesion sesionActualizada = sesionExistente.get();
            sesionActualizada.setNombre(sesion.getNombre());
            sesionActualizada.setDescripcion(sesion.getDescripcion());
            sesionActualizada.setFechaHoraInicio(sesion.getFechaHoraInicio());
            sesionActualizada.setFechaHoraFin(sesion.getFechaHoraFin());

            // Guardar la sesión modificada
            return repository.save(sesionActualizada);
        } else {
            return null;  // O lanzar una excepción si la sesión no se encuentra
        }
    }
}
