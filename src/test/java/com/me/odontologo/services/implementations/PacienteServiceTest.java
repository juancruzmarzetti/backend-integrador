package com.me.odontologo.services.implementations;

import com.me.odontologo.dao.DB;
import com.me.odontologo.model.Domicilio;
import com.me.odontologo.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        DB.crearTablas();
    }

    @Test
    void guardarPaciente() {
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
        Paciente pacienteAlGuardar = pacienteService.guardarPaciente(paciente1);

        Assertions.assertEquals(paciente1.getDni(), pacienteAlGuardar.getDni());
    }

    @Test
    void eliminarPaciente() {
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

        Paciente pacienteAEliminar = pacienteService.guardarPaciente(paciente1);
        Paciente pacienteAChequear = pacienteService.guardarPaciente(paciente2);
        pacienteService.eliminarPaciente(pacienteAEliminar.getId());
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(1, pacientes.size());
    }

    @Test
    void buscarTodosLosPacientes() {
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
        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(2, pacientes.size());
    }

    @Test
    void buscar() {
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
        Paciente pacienteAlGuardar = pacienteService.guardarPaciente(paciente1);
        Paciente pacienteBuscado = pacienteService.buscar(pacienteAlGuardar.getId());
        Assertions.assertEquals(pacienteAlGuardar.getId(), pacienteBuscado.getId());
    }
}