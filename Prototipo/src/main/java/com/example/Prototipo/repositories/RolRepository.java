package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
}
