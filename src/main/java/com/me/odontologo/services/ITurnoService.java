package com.me.odontologo.services;

import com.me.odontologo.model.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);
    void eliminarTurno(int id);
    List<Turno> buscarTodosLosTurnos();
    Turno buscar(int id);
}
