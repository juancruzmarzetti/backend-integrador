package com.me.odontologo.domain;

import java.time.LocalDate;

public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private Integer dni;
    private LocalDate fechaDeAlta;
    private String usuario;
    private String password;
    public Paciente(){};
    public Paciente(int id, String nombre, String apellido, String domicilio,
                    Integer dni, LocalDate fechaDeAlta, String usuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
        this.usuario = usuario;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getDomicilio() {
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
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setDomicilio(String domicilio) {
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
