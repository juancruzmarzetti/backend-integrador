package com.me.odontologo.services;

import com.me.odontologo.domain.Paciente;
import java.util.List;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);
    void eliminarPaciente(int id);
    List<Paciente> buscarTodosLosPacientes();
    Paciente buscar(int id);
}
