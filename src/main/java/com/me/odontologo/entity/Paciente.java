package com.me.odontologo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    private LocalDate fechaDeAlta;
    private String usuario;
    private String password;
    public Paciente(){};
    /*
    public Paciente(String nombre, String apellido, Domicilio domicilio,
                    Integer dni, LocalDate fechaDeAlta, String usuario, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
        this.usuario = usuario;
        this.password = password;
    }
    public Paciente(int id, String nombre, String apellido, Domicilio domicilio,
                    Integer dni, LocalDate fechaDeAlta, String usuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
        this.usuario = usuario;
        this.password = password;
    }*/
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    public Integer getDni() {
        return dni;
    }
    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    public void setDni(Integer dni) {
        this.dni = dni;
    }
    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}
