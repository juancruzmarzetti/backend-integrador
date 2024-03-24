package com.me.odontologo.service;

import com.me.odontologo.dto.PacienteResponseDTO;
import com.me.odontologo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Optional<PacienteResponseDTO> guardarPaciente(Paciente paciente);
    Optional<PacienteResponseDTO> actualizarPaciente(Paciente paciente);
    boolean eliminarPaciente(Long id);
    List<PacienteResponseDTO> buscarTodosLosPacientes();
    Optional<PacienteResponseDTO> buscar(Long id);
}
