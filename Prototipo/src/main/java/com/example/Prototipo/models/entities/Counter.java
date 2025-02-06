package com.example.Prototipo.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class Counter {

    @Id
    private String id;
    private String collectionName;
    private int sequenceValue;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getSequenceValue() {
        return sequenceValue;
    }

    public void setSequenceValue(int sequenceValue) {
        this.sequenceValue = sequenceValue;
    }
}
