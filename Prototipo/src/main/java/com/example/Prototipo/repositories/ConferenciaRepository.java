package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Conferencia;
import org.springframework.data.repository.CrudRepository;

public interface ConferenciaRepository extends CrudRepository<Conferencia, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
}
