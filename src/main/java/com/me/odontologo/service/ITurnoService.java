package com.me.odontologo.service;

import com.me.odontologo.entity.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);
    void eliminarTurno(int id);
    List<Turno> buscarTodosLosTurnos();
    Turno buscar(int id);
}
