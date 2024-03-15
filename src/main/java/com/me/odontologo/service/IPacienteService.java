package com.me.odontologo.service;

import com.me.odontologo.entity.Paciente;
import java.util.List;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);
    void eliminarPaciente(int id);
    List<Paciente> buscarTodosLosPacientes();
    Paciente buscar(int id);
}
