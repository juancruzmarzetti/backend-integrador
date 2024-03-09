package com.me.odontologo.services;

import com.me.odontologo.domain.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);
    void eliminarTurno(int id);
    List<Turno> buscarTodosLosTurnos();
    Turno buscar(int id);
}
