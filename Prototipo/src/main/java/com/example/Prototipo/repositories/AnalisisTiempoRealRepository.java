package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnalisisTiempoRealRepository extends MongoRepository<AnalisisTiempoReal, String> {
    List<AnalisisTiempoReal> findByidSeccion(int idSeccion); // Definir este método
    List<AnalisisTiempoReal> findByIdSeccionIn(List<Integer> idSecciones);

}
