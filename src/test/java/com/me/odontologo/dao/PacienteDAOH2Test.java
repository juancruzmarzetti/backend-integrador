package com.me.odontologo.dao;

import com.me.odontologo.dao.implementations.PacienteDAOH2;
import com.me.odontologo.domain.Domicilio;
import com.me.odontologo.domain.Paciente;
import com.me.odontologo.services.implementations.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

class PacienteDAOH2Test {
    @Test
    public void guardarUno(){
        DB.crearTablas();
        Domicilio domicilio1 = new Domicilio("Pompeya",
                1502, "Pompeya", "Buenos Aires");
        Paciente paciente1 = new Paciente(
                "Nombre1",
                "Apellido1",
                domicilio1,
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        PacienteService pacienteService = new PacienteService();
        Paciente pacienteAlGuardar = pacienteService.guardarPaciente(paciente1);

        Assertions.assertEquals(paciente1.getDni(), pacienteAlGuardar.getDni());
    }

    @Test
    public void buscarUno(){
        DB.crearTablas();
        Domicilio domicilio1 = new Domicilio("Pompeya",
                1502, "Pompeya", "Buenos Aires");
        Paciente paciente1 = new Paciente(
                "Nombre1",
                "Apellido1",
                domicilio1,
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        PacienteService pacienteService = new PacienteService();
        Paciente pacienteAlGuardar = pacienteService.guardarPaciente(paciente1);
        Paciente pacienteBuscado = pacienteService.buscar(pacienteAlGuardar.getId());
        Assertions.assertEquals(pacienteAlGuardar.getId(), pacienteBuscado.getId());
    }


    @Test
    public void eliminarUno(){
        DB.crearTablas();
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Domicilio domicilio1 = new Domicilio("Pompeya",
                1502, "Pompeya", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("Pompeya2",
                1503, "Pompeya2", "Buenos Aires2");
        Paciente paciente1 = new Paciente(
                "Nombre1",
                "Apellido1",
                domicilio1,
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        Paciente paciente2 = new Paciente(
                "Nombre2",
                "Apellido2",
                domicilio2,
                22222222,
                LocalDate.of(2022, 2, 22),
                "usuario2",
                "password2"
        );

        PacienteService pacienteService = new PacienteService();
        Paciente pacienteAEliminar = pacienteService.guardarPaciente(paciente1);
        Paciente pacienteAChequear = pacienteService.guardarPaciente(paciente2);
        pacienteService.eliminarPaciente(pacienteAEliminar.getId());
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(1, pacientes.size());
    }
    @Test
    public void buscarTodo(){
        DB.crearTablas();
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Domicilio domicilio1 = new Domicilio("Pompeya",
                1502, "Pompeya", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("Pompeya2",
                1503, "Pompeya2", "Buenos Aires2");
        Paciente paciente1 = new Paciente(
                "Nombre1",
                "Apellido1",
                domicilio1,
                11111111,
                LocalDate.of(2020, 7, 12),
                "usuario1",
                "password1"
        );
        Paciente paciente2 = new Paciente(
                "Nombre2",
                "Apellido2",
                domicilio2,
                22222222,
                LocalDate.of(2022, 2, 22),
                "usuario2",
                "password2"
        );
        PacienteService pacienteService = new PacienteService();
        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(2, pacientes.size());
    }
}