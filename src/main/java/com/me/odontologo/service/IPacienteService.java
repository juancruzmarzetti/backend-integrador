package com.me.odontologo.service;

import com.me.odontologo.dto.PacienteResponseDTO;
import com.me.odontologo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Optional<PacienteResponseDTO> guardarPaciente(Paciente paciente);
    PacienteResponseDTO actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
    List<PacienteResponseDTO> buscarTodosLosPacientes();
    PacienteResponseDTO buscar(Long id);
}
