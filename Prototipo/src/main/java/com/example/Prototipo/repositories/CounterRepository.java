package com.example.Prototipo.repositories;

import com.example.Prototipo.models.entities.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {
    List<Counter> findByCollectionName(String collectionName);
}
