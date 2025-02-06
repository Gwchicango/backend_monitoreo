package com.example.Prototipo.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "informes")
public class Informe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "objetivos_totales")
    private float objetivosTotales;

    @Column(name = "aciertos")
    private float aciertos;

    @Column(name = "errores_por_omision")
    private float erroresPorOmision;

    @Column(name = "errores_por_comision")
    private float erroresPorComision;

    @Column(name = "errores_letras")
    private float erroresLetras;

    @Column(name = "errores_rayita")
    private float erroresRayita;

    @Column(name = "errores_dobles")
    private float erroresDobles;

    @Column(name = "errores_totales")
    private float erroresTotales;

    @Column(name = "efectividad_total")
    private float efectividadTotal;

    @Column(name = "indice_concentracion")
    private float indiceConcentracion;

    @Column(name = "indice_variacion")
    private float indiceVariacion;

    @Column(name = "velocidad_trabajo")
    private float velocidadTrabajo;

    @Column(name = "precision_info")
    private float precision_info;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "seccion_id", referencedColumnName = "id")
    private SeccionTest seccionTest; // Relación uno a uno con la entidad Rol


    // Getters y Setters
    public long getId() {
        return id;
    }

    public float getObjetivosTotales() {
        return objetivosTotales;
    }

    public float getAciertos() {
        return aciertos;
    }

    public float getErroresPorOmision() {
        return erroresPorOmision;
    }

    public float getErroresPorComision() {
        return erroresPorComision;
    }

    public float getErroresLetras() {
        return erroresLetras;
    }

    public float getErroresRayita() {
        return erroresRayita;
    }

    public SeccionTest getSeccionTest() {
        return seccionTest;
    }

    public void setSeccionTest(SeccionTest seccionTest) {
        this.seccionTest = seccionTest;
    }

    public float getErroresDobles() {
        return erroresDobles;
    }

    public float getErroresTotales() {
        return erroresTotales;
    }

    public float getEfectividadTotal() {
        return efectividadTotal;
    }

    public float getIndiceConcentracion() {
        return indiceConcentracion;
    }

    public float getIndiceVariacion() {
        return indiceVariacion;
    }

    public float getVelocidadTrabajo() {
        return velocidadTrabajo;
    }

    public float getPrecision_info() {
        return precision_info;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setObjetivosTotales(float objetivosTotales) {
        this.objetivosTotales = objetivosTotales;
    }

    public void setAciertos(float aciertos) {
        this.aciertos = aciertos;
    }

    public void setErroresPorOmision(float erroresPorOmision) {
        this.erroresPorOmision = erroresPorOmision;
    }

    public void setErroresPorComision(float erroresPorComision) {
        this.erroresPorComision = erroresPorComision;
    }

    public void setErroresLetras(float erroresLetras) {
        this.erroresLetras = erroresLetras;
    }

    public void setErroresRayita(float erroresRayita) {
        this.erroresRayita = erroresRayita;
    }

    public void setErroresDobles(float erroresDobles) {
        this.erroresDobles = erroresDobles;
    }

    public void setErroresTotales(float erroresTotales) {
        this.erroresTotales = erroresTotales;
    }

    public void setEfectividadTotal(float efectividadTotal) {
        this.efectividadTotal = efectividadTotal;
    }

    public void setIndiceConcentracion(float indiceConcentracion) {
        this.indiceConcentracion = indiceConcentracion;
    }

    public void setIndiceVariacion(float indiceVariacion) {
        this.indiceVariacion = indiceVariacion;
    }

    public void setVelocidadTrabajo(float velocidadTrabajo) {
        this.velocidadTrabajo = velocidadTrabajo;
    }

    public void setPrecision_info(float precision_info) {
        this.precision_info = precision_info;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


}
