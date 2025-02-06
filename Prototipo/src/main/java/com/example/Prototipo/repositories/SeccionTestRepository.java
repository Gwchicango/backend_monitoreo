package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Persona;
import com.example.Prototipo.models.entities.SeccionTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeccionTestRepository extends CrudRepository<SeccionTest, Long> {
    List<SeccionTest> findByPersonaId(int idPersona);
    List<SeccionTest> findByPersona(Persona persona);

}
