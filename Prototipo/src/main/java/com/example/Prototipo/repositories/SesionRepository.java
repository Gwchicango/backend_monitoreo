package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Sesion;
import org.springframework.data.repository.CrudRepository;

public interface SesionRepository extends CrudRepository<Sesion, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
}
