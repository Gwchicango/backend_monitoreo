package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Rol;
import com.example.Prototipo.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repository;

    @Override
    public List<Rol> findAll() {
        return (List<Rol>) repository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {  // Cambiado a Long
        return repository.findById(id);
    }

    @Override
    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public void deleteById(Long id) {  // Cambiado a Long
        repository.deleteById(id);
    }

    @Override
    public Rol modificar(Long id, Rol rol) {  // Cambiado a Long
        Optional<Rol> rolExistente = repository.findById(id);

        if (rolExistente.isPresent()) {
            // Actualizar los campos del rol encontrado con los nuevos valores
            Rol rolActualizado = rolExistente.get();
            rolActualizado.setIdRol(rol.getIdRol());
            rolActualizado.setNombreRol(rol.getNombreRol());

            // Guardar el rol modificado
            return repository.save(rolActualizado);
        } else {
            return null;  // O lanzar una excepción si el rol no se encuentra
        }
    }
}
