package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Counter;
import com.example.Prototipo.repositories.CounterRepository;
import com.example.Prototipo.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;

    @Override
  public int getNextSequence(String collectionName) {
    List<Counter> counters = counterRepository.findByCollectionName(collectionName);
    Counter counter;

    if (counters.isEmpty()) {
        // Si no existe un contador para esta colección, creamos uno
        counter = new Counter();
        counter.setCollectionName(collectionName);
        counter.setSequenceValue(1);
    } else {
        // Obtenemos el primer contador de la lista y lo incrementamos
        counter = counters.get(0);
        counter.setSequenceValue(counter.getSequenceValue() + 1);
    }

    // Guardamos el contador actualizado
    counterRepository.save(counter);

    return counter.getSequenceValue();
}
}
