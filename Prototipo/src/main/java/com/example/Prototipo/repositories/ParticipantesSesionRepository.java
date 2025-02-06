package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParticipantesSesionRepository extends MongoRepository<ParticipantesSesion, String> {
    List<ParticipantesSesion> findByidSeccion(int idSeccion); // Definir este método
    List<ParticipantesSesion> findByIdSeccionIn(List<Integer> idSecciones);

}
