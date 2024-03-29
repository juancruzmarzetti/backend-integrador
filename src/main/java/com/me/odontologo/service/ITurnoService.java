package com.me.odontologo.service;

import com.me.odontologo.dto.request.TurnoRequestDTO;
import com.me.odontologo.dto.response.TurnoResponseDTO;
import com.me.odontologo.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDTO guardarTurno(TurnoRequestDTO turnoRequest);
    void eliminarTurno(Long id);
    List<TurnoResponseDTO> buscarTodosLosTurnos();
    TurnoResponseDTO buscar(Long id);
    TurnoResponseDTO actualizarTurno(Turno turno);
}
