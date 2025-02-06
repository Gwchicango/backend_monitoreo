package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;
import com.example.Prototipo.models.entities.Persona;
import com.example.Prototipo.models.entities.SeccionTest;
import com.example.Prototipo.repositories.AnalisisTiempoRealRepository;
import com.example.Prototipo.repositories.PersonaRepository;
import com.example.Prototipo.repositories.SeccionTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnalisisTiempoRealServiceImpl implements AnalisisTiempoRealService {

    @Autowired
    private AnalisisTiempoRealRepository repository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private SeccionTestRepository seccionTestRepository;


    @Autowired
    private CounterService counterService;  // Inyectamos el servicio del contador

    @Override
    public List<AnalisisTiempoReal> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AnalisisTiempoReal> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public AnalisisTiempoReal save(AnalisisTiempoReal analisisTiempoReal) {
        // Generamos el siguiente valor de idAnalisis antes de guardar
        int nextIdAnalisis = counterService.getNextSequence("analisistiemporeal");
        analisisTiempoReal.setIdAnalisis(nextIdAnalisis);

        return repository.save(analisisTiempoReal);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public AnalisisTiempoReal modificar(String id, AnalisisTiempoReal analisisTiempoReal) {
        Optional<AnalisisTiempoReal> analisisExistente = repository.findById(id);

        if (analisisExistente.isPresent()) {
            AnalisisTiempoReal analisisActualizado = analisisExistente.get();
            analisisActualizado.setIdAnalisis(analisisTiempoReal.getIdAnalisis());
            analisisActualizado.setActivacion(analisisTiempoReal.getActivacion());
            analisisActualizado.setValencia(analisisTiempoReal.getValencia());
            analisisActualizado.setAtencion(analisisTiempoReal.getAtencion());

            return repository.save(analisisActualizado);
        } else {
            return null;
        }
    }

    @Override
    public List<AnalisisTiempoReal> findByidSeccion(int idSeccion) {
        return repository.findByidSeccion(idSeccion); // Implementar este método en el repositorio
    }

    @Override
    public List<AnalisisTiempoReal> obtenerParticipantesPorNrc(String nrc) {
        // Obtener todas las personas que tienen el nrc
        List<Persona> personas = personaRepository.findByNrc(nrc);
        // Si no se encuentran personas, devolvemos una lista vacía
        if (personas.isEmpty()) {
            return new ArrayList<>();
        }
        // Obtener los IDs de las secciones relacionadas con estas personas
        List<Integer> idSecciones = new ArrayList<>();
        for (Persona persona : personas) {
            List<SeccionTest> secciones = seccionTestRepository.findByPersona(persona);
            for (SeccionTest seccion : secciones) {
                idSecciones.add(seccion.getIdSeccion());
            }
        }
        // Si no se encuentran secciones, devolvemos una lista vacía
        if (idSecciones.isEmpty()) {
            return new ArrayList<>();
        }
        // Obtener los participantes de esas secciones
        List<AnalisisTiempoReal> participantes = repository.findByIdSeccionIn(idSecciones);
        // Si no se encuentran participantes, devolvemos una lista vacía
        if (participantes.isEmpty()) {
            return new ArrayList<>();
        }
        return participantes;
    }

}
