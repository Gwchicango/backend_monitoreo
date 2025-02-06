package com.example.Prototipo.services;


import com.example.Prototipo.models.entities.Conferencia;
import com.example.Prototipo.repositories.ConferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenciaServiceImpl implements ConferenciaService {

    @Autowired
    private ConferenciaRepository repository;

    @Override
    public List<Conferencia> findAll() {
        return (List<Conferencia>) repository.findAll();
    }

    @Override
    public Optional<Conferencia> findById(Long id) {  // Cambiado a Long
        return repository.findById(id);
    }

    @Override
    public Conferencia save(Conferencia conferencia) {
        return repository.save(conferencia);
    }

    @Override
    public void deleteById(Long id) {  // Cambiado a Long
        repository.deleteById(id);
    }

    @Override
    public Conferencia modificar(Long id, Conferencia conferencia) {  // Cambiado a Long
        Optional<Conferencia> conferenciaExistente = repository.findById(id);

        if (conferenciaExistente.isPresent()) {
            // Actualizar los campos de la conferencia encontrada con los nuevos valores
            Conferencia conferenciaActualizada = conferenciaExistente.get();
            conferenciaActualizada.setTitulo(conferencia.getTitulo());
            conferenciaActualizada.setDescripcion(conferencia.getDescripcion());
            conferenciaActualizada.setFechaInicio(conferencia.getFechaInicio());
            conferenciaActualizada.setFechaFin(conferencia.getFechaFin());

            // Guardar la conferencia modificada
            return repository.save(conferenciaActualizada);
        } else {
            return null;  // O lanzar una excepción si la conferencia no se encuentra
        }
    }
}
