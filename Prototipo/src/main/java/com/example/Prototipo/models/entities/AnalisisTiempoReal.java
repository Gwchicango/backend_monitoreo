package com.example.Prototipo.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "analisistiemporeal")
public class AnalisisTiempoReal {

    @Id
    private String id;

    private int idAnalisis;  // Cambiado a idAnalisis y debe ser incremental
    private int idSeccion;  // Nueva variable agregada
    private Double activacion;
    private Double valencia;
    private Double atencion;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public Double getActivacion() {
        return activacion;
    }

    public Double getValencia() {
        return valencia;
    }

    public Double getAtencion() {
        return atencion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setActivacion(Double activacion) {
        this.activacion = activacion;
    }

    public void setValencia(Double valencia) {
        this.valencia = valencia;
    }

    public void setAtencion(Double atencion) {
        this.atencion = atencion;
    }
}
