package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Informe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InformeRepository extends CrudRepository<Informe, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
    List<Informe> findBySeccionTest_Id(long id);

    // Método para encontrar informes por NRC
    @Query("SELECT i FROM Informe i WHERE i.seccionTest.persona.nrc = :nrc")
    List<Informe> findByPersonaNrc(String nrc);

}
