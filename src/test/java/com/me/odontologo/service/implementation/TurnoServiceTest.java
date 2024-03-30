package com.me.odontologo.service.implementation;

import com.me.odontologo.dto.request.OdontologoRequestDTO;
import com.me.odontologo.dto.request.PacienteRequestDTO;
import com.me.odontologo.dto.request.TurnoRequestDTO;
import com.me.odontologo.dto.response.OdontologoResponseDTO;
import com.me.odontologo.dto.response.PacienteResponseDTO;
import com.me.odontologo.dto.response.TurnoResponseDTO;
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


        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        OdontologoResponseDTO odontologoResp = odontologoService.guardarOdontologo(odontologo1);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        Optional<PacienteResponseDTO> pacienteResp = pacienteService.guardarPaciente(paciente1);

        TurnoRequestDTO turno1 = new TurnoRequestDTO();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologoResp.getId());
        turno1.setPaciente(pacienteResp.get().getId());

        TurnoResponseDTO turnoResp = turnoService.guardarTurno(turno1);

        TurnoResponseDTO turnoBuscado = turnoService.buscar(turnoResp.getId());

        Assertions.assertNotNull(turnoBuscado);

        turnoService.eliminarTurno(turnoResp.getId());
        odontologoService.eliminarOdontologo(odontologoResp.getId());
        pacienteService.eliminarPaciente(pacienteResp.get().getId());
    }

    @Test
    void eliminarTurno() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        OdontologoResponseDTO odontologoResp1 = odontologoService.guardarOdontologo(odontologo1);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        Optional<PacienteResponseDTO> pacienteResp1 = pacienteService.guardarPaciente(paciente1);

        TurnoRequestDTO turno1 = new TurnoRequestDTO();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologoResp1.getId());
        turno1.setPaciente(pacienteResp1.get().getId());

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setNumero(2222);
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Catamarca2");


        OdontologoRequestDTO odontologo2 = new OdontologoRequestDTO();
        odontologo2.setNombre("Jorge2");
        odontologo2.setApellido("Lopez2");
        odontologo2.setMatricula(22222222);
        OdontologoResponseDTO odontologoResp2 = odontologoService.guardarOdontologo(odontologo2);

        PacienteRequestDTO paciente2 = new PacienteRequestDTO();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez Junior2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(222222);
        paciente2.setFechaDeAlta(LocalDate.of(2022, 2, 12));
        Optional<PacienteResponseDTO> pacienteResp2 = pacienteService.guardarPaciente(paciente2);

        TurnoRequestDTO turno2 = new TurnoRequestDTO();
        turno2.setFecha(LocalDate.of(2022,2, 22));
        turno2.setHora(LocalTime.of(22, 22, 22));
        turno2.setOdontologo(odontologoResp2.getId());
        turno2.setPaciente(pacienteResp2.get().getId());

        TurnoResponseDTO turnoResp1 = turnoService.guardarTurno(turno1);
        TurnoResponseDTO turnoResp2 = turnoService.guardarTurno(turno2);
        turnoService.eliminarTurno(turnoResp1.getId());
        List<TurnoResponseDTO> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(1, turnos.size());

        turnoService.eliminarTurno(turnoResp2.getId());
        odontologoService.eliminarOdontologo(odontologoResp1.getId());
        odontologoService.eliminarOdontologo(odontologoResp2.getId());
        pacienteService.eliminarPaciente(pacienteResp1.get().getId());
        pacienteService.eliminarPaciente(pacienteResp2.get().getId());
    }

    @Test
    void buscarTodosLosTurnos() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        OdontologoResponseDTO odontologoResp1 = odontologoService.guardarOdontologo(odontologo1);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        Optional<PacienteResponseDTO> pacienteResp1 = pacienteService.guardarPaciente(paciente1);

        TurnoRequestDTO turno1 = new TurnoRequestDTO();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologoResp1.getId());
        turno1.setPaciente(pacienteResp1.get().getId());

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setNumero(2222);
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Catamarca2");


        OdontologoRequestDTO odontologo2 = new OdontologoRequestDTO();
        odontologo2.setNombre("Jorge2");
        odontologo2.setApellido("Lopez2");
        odontologo2.setMatricula(22222222);
        OdontologoResponseDTO odontologoResp2 = odontologoService.guardarOdontologo(odontologo2);

        PacienteRequestDTO paciente2 = new PacienteRequestDTO();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez Junior2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(222222);
        paciente2.setFechaDeAlta(LocalDate.of(2022, 2, 12));

        Optional<PacienteResponseDTO> pacienteResp2 = pacienteService.guardarPaciente(paciente2);

        TurnoRequestDTO turno2 = new TurnoRequestDTO();
        turno2.setFecha(LocalDate.of(2022,2, 22));
        turno2.setHora(LocalTime.of(22, 22, 22));
        turno2.setOdontologo(odontologoResp2.getId());
        turno2.setPaciente(pacienteResp2.get().getId());

        TurnoResponseDTO turnoResp1 = turnoService.guardarTurno(turno1);
        TurnoResponseDTO turnoResp2 = turnoService.guardarTurno(turno2);
        List<TurnoResponseDTO> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(2, turnos.size());

        turnoService.eliminarTurno(turnoResp1.getId());
        turnoService.eliminarTurno(turnoResp2.getId());
        odontologoService.eliminarOdontologo(odontologoResp1.getId());
        odontologoService.eliminarOdontologo(odontologoResp2.getId());
        pacienteService.eliminarPaciente(pacienteResp1.get().getId());
        pacienteService.eliminarPaciente(pacienteResp2.get().getId());
    }

    @Test
    void buscar() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        OdontologoResponseDTO odontologoResp = odontologoService.guardarOdontologo(odontologo1);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        Optional<PacienteResponseDTO> pacienteResp = pacienteService.guardarPaciente(paciente1);

        TurnoRequestDTO turno1 = new TurnoRequestDTO();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologoResp.getId());
        turno1.setPaciente(pacienteResp.get().getId());

        TurnoResponseDTO turnoResp = turnoService.guardarTurno(turno1);

        TurnoResponseDTO turnoBuscado = turnoService.buscar(turnoResp.getId());

        Assertions.assertNotNull(turnoBuscado);

        turnoService.eliminarTurno(turnoResp.getId());
        odontologoService.eliminarOdontologo(odontologoResp.getId());
        pacienteService.eliminarPaciente(pacienteResp.get().getId());
    }

    @Test
    void actualizarTurno(){
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(1509);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Catamarca");


        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula(523124);
        OdontologoResponseDTO odontologoResp1 = odontologoService.guardarOdontologo(odontologo1);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez Junior");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12121212);
        paciente1.setFechaDeAlta(LocalDate.of(2023, 7, 15));
        Optional<PacienteResponseDTO> pacienteResp1 = pacienteService.guardarPaciente(paciente1);

        TurnoRequestDTO turno1 = new TurnoRequestDTO();
        turno1.setFecha(LocalDate.of(2023,5, 15));
        turno1.setHora(LocalTime.of(15, 30, 0));
        turno1.setOdontologo(odontologoResp1.getId());
        turno1.setPaciente(pacienteResp1.get().getId());

        TurnoResponseDTO turnoResp = turnoService.guardarTurno(turno1);

        OdontologoRequestDTO odontologo2 = new OdontologoRequestDTO();
        odontologo2.setNombre("Jorge2");
        odontologo2.setApellido("Lopez2");
        odontologo2.setMatricula(22222222);
        OdontologoResponseDTO odontologoResp2 = odontologoService.guardarOdontologo(odontologo2);

        Odontologo odontologoActualizarTurno = new Odontologo();
        odontologoActualizarTurno.setId(odontologoResp2.getId());

        Paciente pacienteActualizarTurno = new Paciente();
        pacienteActualizarTurno.setId(pacienteResp1.get().getId());

        Turno turnoActualizar = new Turno();
        turnoActualizar.setId(turnoResp.getId());
        turnoActualizar.setOdontologo(odontologoActualizarTurno);
        turnoActualizar.setPaciente(pacienteActualizarTurno);
        turnoActualizar.setHora(turnoResp.getHora());
        turnoActualizar.setFecha(turnoResp.getFecha());

        TurnoResponseDTO turnoActualizado = turnoService.actualizarTurno(turnoActualizar);

        Assertions.assertEquals(turnoActualizar.getOdontologo().getId(), turnoActualizado.getOdontologo());

        turnoService.eliminarTurno(turnoResp.getId());
        odontologoService.eliminarOdontologo(odontologoResp1.getId());
        odontologoService.eliminarOdontologo(odontologoResp2.getId());
        pacienteService.eliminarPaciente(pacienteResp1.get().getId());
    }
}