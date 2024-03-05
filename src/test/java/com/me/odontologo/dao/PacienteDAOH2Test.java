package com.me.odontologo.dao;

import com.me.odontologo.domain.Paciente;
import com.me.odontologo.services.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

class PacienteDAOH2Test {

    @Test
    public void guardarYBuscarTodos(){
        Paciente paciente1 = new Paciente(
                1,
                "Nombre1",
                "Apellido1",
                "Domicilio1",
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        Paciente paciente2 = new Paciente(
                2,
                "Nombre2",
                "Apellido2",
                "Domicilio2",
                22222222,
                LocalDate.of(2022, 2, 22),
                "usuario2",
                "password2"
        );
        PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(2, pacientes.size());
    }
    @Test
    public void eliminarUno(){
        Paciente paciente1 = new Paciente(
                1,
                "Nombre1",
                "Apellido1",
                "Domicilio1",
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        Paciente paciente2 = new Paciente(
                2,
                "Nombre2",
                "Apellido2",
                "Domicilio2",
                22222222,
                LocalDate.of(2022, 2, 22),
                "usuario2",
                "password2"
        );
        PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);
        pacienteService.eliminarPaciente(paciente2.getId());
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(1, pacientes.size());
    }

    @Test
    public void buscarUno(){
        Paciente paciente1 = new Paciente(
                1,
                "Nombre1",
                "Apellido1",
                "Domicilio1",
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        Paciente paciente2 = new Paciente(
                2,
                "Nombre2",
                "Apellido2",
                "Domicilio2",
                22222222,
                LocalDate.of(2022, 2, 22),
                "usuario2",
                "password2"
        );
        PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);

        Assertions.assertEquals(1, pacienteService.buscar(paciente1.getId()).getId());
    }
}