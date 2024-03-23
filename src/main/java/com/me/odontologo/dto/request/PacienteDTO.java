package com.me.odontologo.dto.request;

import com.me.odontologo.entity.Domicilio;

import java.time.LocalDate;

public class PacienteDTO {
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    private LocalDate fechaDeAlta;
    private String usuario;
    private String password;
}
