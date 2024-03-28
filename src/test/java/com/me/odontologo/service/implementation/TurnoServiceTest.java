package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Domicilio;
import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.entity.Turno;
import com.me.odontologo.service.IOdontologoService;
import com.me.odontologo.service.IPacienteService;
import com.me.odontologo.service.ITurnoService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    void guardarTurno() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        odontologoService.guardarOdontologo(odontologo1);

        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        paciente1.setUsuario("jorgitojunior123");
        paciente1.setPassword("123");
        pacienteService.guardarPaciente(paciente1);

        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologo1);
        turno1.setPaciente(paciente1);

        turnoService.guardarTurno(turno1);

        Optional<Turno> turnoBuscado = turnoService.buscar(turno1.getId());

        Assertions.assertTrue(turnoBuscado.isPresent());

        turnoService.eliminarTurno(turnoBuscado.get().getId());
        odontologoService.eliminarOdontologo(odontologo1.getId());
        pacienteService.eliminarPaciente(paciente1.getId());
    }

    @Test
    void eliminarTurno() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        odontologoService.guardarOdontologo(odontologo1);

        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        paciente1.setUsuario("jorgitojunior123");
        paciente1.setPassword("123");
        pacienteService.guardarPaciente(paciente1);

        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologo1);
        turno1.setPaciente(paciente1);

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setNumero(2222);
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Catamarca2");


        Odontologo odontologo2 = new Odontologo();
        odontologo2.setNombre("Jorge2");
        odontologo2.setApellido("Lopez2");
        odontologo2.setMatricula(22222222);
        odontologoService.guardarOdontologo(odontologo2);

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez Junior2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(222222);
        paciente2.setFechaDeAlta(LocalDate.of(2022, 2, 12));
        paciente2.setUsuario("jorgitojunior222");
        paciente2.setPassword("222");
        pacienteService.guardarPaciente(paciente2);

        Turno turno2 = new Turno();
        turno2.setFecha(LocalDate.of(2022,2, 22));
        turno2.setHora(LocalTime.of(22, 22, 22));
        turno2.setOdontologo(odontologo2);
        turno2.setPaciente(paciente2);

        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        turnoService.eliminarTurno(turno1.getId());
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(1, turnos.size());

        turnoService.eliminarTurno(turno2.getId());
        odontologoService.eliminarOdontologo(odontologo1.getId());
        odontologoService.eliminarOdontologo(odontologo2.getId());
        pacienteService.eliminarPaciente(paciente1.getId());
        pacienteService.eliminarPaciente(paciente2.getId());
    }

    @Test
    void buscarTodosLosTurnos() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        odontologoService.guardarOdontologo(odontologo1);

        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        paciente1.setUsuario("jorgitojunior123");
        paciente1.setPassword("123");
        pacienteService.guardarPaciente(paciente1);

        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologo1);
        turno1.setPaciente(paciente1);

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setNumero(2222);
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Catamarca2");


        Odontologo odontologo2 = new Odontologo();
        odontologo2.setNombre("Jorge2");
        odontologo2.setApellido("Lopez2");
        odontologo2.setMatricula(22222222);
        odontologoService.guardarOdontologo(odontologo2);

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez Junior2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(222222);
        paciente2.setFechaDeAlta(LocalDate.of(2022, 2, 12));
        paciente2.setUsuario("jorgitojunior222");
        paciente2.setPassword("222");
        pacienteService.guardarPaciente(paciente2);

        Turno turno2 = new Turno();
        turno2.setFecha(LocalDate.of(2022,2, 22));
        turno2.setHora(LocalTime.of(22, 22, 22));
        turno2.setOdontologo(odontologo2);
        turno2.setPaciente(paciente2);

        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(2, turnos.size());
        turnoService.eliminarTurno(turno1.getId());
        turnoService.eliminarTurno(turno2.getId());
    }

    @Test
    void buscar() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        odontologoService.guardarOdontologo(odontologo1);

        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        paciente1.setUsuario("jorgitojunior123");
        paciente1.setPassword("123");
        pacienteService.guardarPaciente(paciente1);

        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologo1);
        turno1.setPaciente(paciente1);

        turnoService.guardarTurno(turno1);
        Optional<Turno> turnoBuscado = turnoService.buscar(turno1.getId());

        Assertions.assertTrue(turnoBuscado.isPresent());

        turnoService.eliminarTurno(turno1.getId());
        odontologoService.eliminarOdontologo(odontologo1.getId());
        pacienteService.eliminarPaciente(paciente1.getId());
    }

    @Test
    void actualizarTurno(){
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        odontologoService.guardarOdontologo(odontologo1);

        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        paciente1.setUsuario("jorgitojunior123");
        paciente1.setPassword("123");
        pacienteService.guardarPaciente(paciente1);

        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologo1);
        turno1.setPaciente(paciente1);

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setNumero(2222);
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Catamarca2");

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez Junior2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(222222);
        paciente2.setFechaDeAlta(LocalDate.of(2022, 2, 12));
        paciente2.setUsuario("jorgitojunior222");
        paciente2.setPassword("222");
        pacienteService.guardarPaciente(paciente2);

        turnoService.guardarTurno(turno1);

        turno1.setPaciente(paciente2);
        turnoService.actualizarTurno(turno1);

        Assertions.assertEquals(turno1.getPaciente().getId(), paciente2.getId());

        turnoService.eliminarTurno(turno1.getId());
        odontologoService.eliminarOdontologo(odontologo1.getId());
        pacienteService.eliminarPaciente(paciente1.getId());
        pacienteService.eliminarPaciente(paciente2.getId());
    }
}