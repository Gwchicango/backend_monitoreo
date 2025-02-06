package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Informe;
import com.example.Prototipo.repositories.InformeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformeServiceImpl implements InformeService {

    @Autowired
    private InformeRepository repository;

    @Override
    public List<Informe> findAll() {
        return (List<Informe>) repository.findAll();
    }

    @Override
    public Optional<Informe> findById(Long id) {  // Cambiado a Long
        return repository.findById(id);
    }

    @Override
    public Informe save(Informe informe) {
        return repository.save(informe);
    }

    @Override
    public void deleteById(Long id) {  // Cambiado a Long
        repository.deleteById(id);
    }

    @Override
    public Informe modificar(Long id, Informe informe) {  // Cambiado a Long
        Optional<Informe> informeExistente = repository.findById(id);

        if (informeExistente.isPresent()) {
            // Actualizar los campos del informe encontrado con los nuevos valores
            Informe informeActualizado = informeExistente.get();
            informeActualizado.setObjetivosTotales(informe.getObjetivosTotales());
            informeActualizado.setAciertos(informe.getAciertos());
            informeActualizado.setErroresPorOmision(informe.getErroresPorOmision());
            informeActualizado.setErroresPorComision(informe.getErroresPorComision());
            informeActualizado.setErroresLetras(informe.getErroresLetras());
            informeActualizado.setErroresRayita(informe.getErroresRayita());
            informeActualizado.setErroresDobles(informe.getErroresDobles());
            informeActualizado.setErroresTotales(informe.getErroresTotales());
            informeActualizado.setEfectividadTotal(informe.getEfectividadTotal());
            informeActualizado.setIndiceConcentracion(informe.getIndiceConcentracion());
            informeActualizado.setIndiceVariacion(informe.getIndiceVariacion());
            informeActualizado.setVelocidadTrabajo(informe.getVelocidadTrabajo());
            informeActualizado.setPrecision_info(informe.getPrecision_info());
            informeActualizado.setFechaCreacion(informe.getFechaCreacion());// Nueva relación con Persona

            // Guardar el informe modificado
            return repository.save(informeActualizado);
        } else {
            return null;  // O lanzar una excepción si el informe no se encuentra
        }
    }

    @Override
    public List<Informe> getInformesBySeccionTestId(long id) {
        return repository.findBySeccionTest_Id(id);  // Llamada al repositorio
    }

    public List<Informe> obtenerInformesPorNrc(String nrc) {
        return repository.findByPersonaNrc(nrc);
    }

}
