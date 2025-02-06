package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;
import com.example.Prototipo.models.entities.Persona;
import com.example.Prototipo.models.entities.SeccionTest;
import com.example.Prototipo.repositories.ParticipantesSesionRepository;
import com.example.Prototipo.repositories.PersonaRepository;
import com.example.Prototipo.repositories.SeccionTestRepository;
import com.example.Prototipo.services.CounterService;  // Importamos el servicio del contador
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantesSesionServiceImpl implements ParticipantesSesionService {

    @Autowired
    private ParticipantesSesionRepository repository;

    @Autowired
    private SeccionTestRepository seccionTestRepository;



    @Autowired
    private CounterService counterService;  // Inyectamos el servicio del contador

    @Override
    public List<ParticipantesSesion> findAll() {
        return repository.findAll();
    }

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Optional<ParticipantesSesion> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public ParticipantesSesion save(ParticipantesSesion participantesSesion) {
        // Generamos el siguiente valor para idParticipanteSesion
        int nextIdParticipanteSesion = counterService.getNextSequence("participantessesiones");
        participantesSesion.setIdParticipacion(nextIdParticipanteSesion);

        return repository.save(participantesSesion);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public ParticipantesSesion modificar(String id, ParticipantesSesion participantesSesion) {
        Optional<ParticipantesSesion> participacionExistente = repository.findById(id);

        if (participacionExistente.isPresent()) {
            // Actualizar los campos de la participación con los nuevos valores
            ParticipantesSesion participacionActualizada = participacionExistente.get();
            participacionActualizada.setAngry(participantesSesion.getAngry());
            participacionActualizada.setDisgust(participantesSesion.getDisgust());
            participacionActualizada.setFear(participantesSesion.getFear());
            participacionActualizada.setHappy(participantesSesion.getHappy());
            participacionActualizada.setSad(participantesSesion.getSad());
            participacionActualizada.setSurprise(participantesSesion.getSurprise());
            participacionActualizada.setNeutral(participantesSesion.getNeutral());

            // Guardar la participación actualizada
            return repository.save(participacionActualizada);
        } else {
            return null;  // O lanzar una excepción si la participación no se encuentra
        }
    }

    @Override
    public List<ParticipantesSesion> findByidSeccion(int idSeccion) {
        return repository.findByidSeccion(idSeccion); // Implementar este método en el repositorio
    }

        @Override
        public List<ParticipantesSesion> obtenerParticipantesPorNrc(String nrc) {
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
            List<ParticipantesSesion> participantes = repository.findByIdSeccionIn(idSecciones);
            // Si no se encuentran participantes, devolvemos una lista vacía
            if (participantes.isEmpty()) {
                return new ArrayList<>();
            }
            return participantes;
        }

}
