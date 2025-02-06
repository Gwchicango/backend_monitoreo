package com.example.Prototipo.models.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_persona")
    private int idPersona;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id", nullable = false)
    private Rol rol;  // Relación con la entidad Rol (clave foránea)

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "username", unique = true, nullable = false)
    private String username; // Campo para el login

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "email")
    private String email;

    @Column(name = "contraseña")
    private String contraseña;


    @Column(name = "nrc")
    private String nrc;

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public Rol getRol() {
        return rol;
    }


    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
