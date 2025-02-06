package com.example.Prototipo.services;

import com.example.Prototipo.models.entities.Counter;

public interface CounterService {

    int getNextSequence(String collectionName);  // Obtiene el siguiente valor de secuencia
}
