package com.me.odontologo.service;

import com.me.odontologo.dto.request.PacienteRequestDTO;
import com.me.odontologo.dto.response.PacienteResponseDTO;
import com.me.odontologo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Optional<PacienteResponseDTO> guardarPaciente(PacienteRequestDTO pacienteRequest);
    PacienteResponseDTO actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
    List<PacienteResponseDTO> buscarTodosLosPacientes();
    PacienteResponseDTO buscar(Long id);
}
