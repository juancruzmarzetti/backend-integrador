package com.me.odontologo.service;

import com.me.odontologo.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);
    void eliminarTurno(Long id);
    List<Turno> buscarTodosLosTurnos();
    Optional<Turno> buscar(Long id);
    Turno actualizarTurno(Turno turno);
}
