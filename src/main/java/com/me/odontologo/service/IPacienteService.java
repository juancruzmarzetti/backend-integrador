package com.me.odontologo.service;

import com.me.odontologo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
    List<Paciente> buscarTodosLosPacientes();
    Optional<Paciente> buscar(Long id);
}
