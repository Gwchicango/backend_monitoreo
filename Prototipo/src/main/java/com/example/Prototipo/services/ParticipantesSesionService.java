package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;

import java.util.List;
import java.util.Optional;

public interface ParticipantesSesionService {

    List<ParticipantesSesion> findAll();
    Optional<ParticipantesSesion> findById(String id);  // Cambiado a String
    ParticipantesSesion save(ParticipantesSesion participantesSesion);
    void deleteById(String id);  // Cambiado a String
    List<ParticipantesSesion> findByidSeccion(int idSeccion);
    ParticipantesSesion modificar(String id, ParticipantesSesion participantesSesion);
    public List<ParticipantesSesion> obtenerParticipantesPorNrc(String nrc) ;

    }
