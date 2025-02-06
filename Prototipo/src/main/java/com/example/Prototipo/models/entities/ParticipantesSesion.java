package com.example.Prototipo.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "participantessesiones")
public class ParticipantesSesion {

    @Id
    private String id;

    private int idParticipacion;  // Este será autoincremental
    private int idSeccion;  // Nueva variable agregada
    private short angry;
    private short disgust;
    private short fear;
    private short happy;
    private short sad;
    private short surprise;
    private short neutral;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public int getIdParticipacion() {
        return idParticipacion;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public short getAngry() {
        return angry;
    }

    public short getDisgust() {
        return disgust;
    }

    public short getFear() {
        return fear;
    }

    public short getHappy() {
        return happy;
    }

    public short getSad() {
        return sad;
    }

    public short getSurprise() {
        return surprise;
    }

    public short getNeutral() {
        return neutral;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdParticipacion(int idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setAngry(short angry) {
        this.angry = angry;
    }

    public void setDisgust(short disgust) {
        this.disgust = disgust;
    }

    public void setFear(short fear) {
        this.fear = fear;
    }

    public void setHappy(short happy) {
        this.happy = happy;
    }

    public void setSad(short sad) {
        this.sad = sad;
    }

    public void setSurprise(short surprise) {
        this.surprise = surprise;
    }

    public void setNeutral(short neutral) {
        this.neutral = neutral;
    }
}
