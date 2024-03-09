package com.me.odontologo.domain;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private int id;
    private Odontologo odontologo;
    private Paciente paciente;
    private LocalDate fecha;
    private LocalTime hora;
    public Turno(){};
    public Turno(int id, Odontologo odontologo, Paciente paciente, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
