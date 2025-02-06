package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Informe;

import java.util.List;
import java.util.Optional;

public interface InformeService {

    List<Informe> findAll();
    Optional<Informe> findById(Long id);  // Cambiado a Long
    Informe save(Informe informe);
    void deleteById(Long id);  // Cambiado a Long
    Informe modificar(Long id, Informe informe);  // Cambiado a Long
    List<Informe> getInformesBySeccionTestId(long id);
    List<Informe> obtenerInformesPorNrc(String nrc);

}
