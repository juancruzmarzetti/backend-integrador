package com.me.odontologo.domain;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    public Turno(){};
    public Turno(int id, LocalDate fecha, LocalTime hora) {
        this.id = id;
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
}
