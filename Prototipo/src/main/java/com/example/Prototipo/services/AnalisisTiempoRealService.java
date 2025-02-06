package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.AnalisisTiempoReal;
import com.example.Prototipo.models.entities.ParticipantesSesion;

import java.util.List;
import java.util.Optional;

public interface AnalisisTiempoRealService {

    List<AnalisisTiempoReal> findAll();
    Optional<AnalisisTiempoReal> findById(String id);  // Cambiado a String
    AnalisisTiempoReal save(AnalisisTiempoReal analisisTiempoReal);
    void deleteById(String id);  // Cambiado a String
    List<AnalisisTiempoReal> findByidSeccion(int idSeccion); // Agregar este método
    AnalisisTiempoReal modificar(String id, AnalisisTiempoReal analisisTiempoReal);
    public List<AnalisisTiempoReal> obtenerParticipantesPorNrc(String nrc) ;

}
