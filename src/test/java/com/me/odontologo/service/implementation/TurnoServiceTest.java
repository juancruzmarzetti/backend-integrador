package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Domicilio;
import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.entity.Turno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
class TurnoServiceTest {
    /*
    @Autowired
    private TurnoService turnoService;

    @BeforeEach
    void setUp(){
        turnoService.eliminarTurno(1);
        turnoService.eliminarTurno(2);
    }

    @Test
    void guardarTurno() {
        Domicilio domicilio1 = new Domicilio("Pompeya", 1509, "Gran Pompeya",
                "Catamarca"
        );
        Odontologo odontologo1 = new Odontologo(523124, "jorge", "lopez");
        Paciente paciente1 = new Paciente("jorge", "lopez junior", domicilio1, 12121212, LocalDate.of(2023, 7, 15),
                "jorgitojunior", "123"
        );
        Turno turno1 = new Turno(1, odontologo1, paciente1,
                LocalDate.of(2023,5, 15),
                LocalTime.of(15, 30, 0)
        );

        turnoService.guardarTurno(turno1);
        Turno turnoBuscado = turnoService.buscar(turno1.getId());

        Assertions.assertEquals(turno1.getId(), turnoBuscado.getId());
    }

    @Test
    void eliminarTurno() {
        Domicilio domicilio1 = new Domicilio("Pompeya", 1509, "Gran Pompeya",
                "Catamarca"
        );
        Odontologo odontologo1 = new Odontologo(523124, "jorge", "lopez");
        Paciente paciente1 = new Paciente("jorge", "lopez junior", domicilio1, 12121212, LocalDate.of(2023, 7, 15),
                "jorgitojunior", "123"
        );
        Turno turno1 = new Turno(1, odontologo1, paciente1,
                LocalDate.of(2023,5, 15),
                LocalTime.of(15, 30, 0)
        );

        turnoService.guardarTurno(turno1);
        turnoService.eliminarTurno(turno1.getId());
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(0, turnos.size());
    }

    @Test
    void buscarTodosLosTurnos() {
        Domicilio domicilio1 = new Domicilio("Pompeya", 1509, "Gran Pompeya",
                "Catamarca"
        );
        Odontologo odontologo1 = new Odontologo(523124, "jorge", "lopez");
        Paciente paciente1 = new Paciente("jorge", "lopez junior", domicilio1, 12121212, LocalDate.of(2023, 7, 15),
                "jorgitojunior", "123"
        );
        Turno turno1 = new Turno(1, odontologo1, paciente1,
                LocalDate.of(2023,5, 15),
                LocalTime.of(15, 30, 0)
        );
        Domicilio domicilio2 = new Domicilio("Pompeya2", 1502, "Gran Pompeya2",
                "Catamarca2"
        );
        Odontologo odontologo2 = new Odontologo(5231242, "jorge2", "lopez2");
        Paciente paciente2 = new Paciente("jorge2", "lopez junior2", domicilio2, 12121212, LocalDate.of(2022, 2, 12),
                "jorgitojunior2", "222"
        );
        Turno turno2 = new Turno(2, odontologo1, paciente1,
                LocalDate.of(2022,2, 2),
                LocalTime.of(22, 32, 2)
        );

        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(2, turnos.size());
    }

    @Test
    void buscar() {
        Domicilio domicilio1 = new Domicilio("Pompeya", 1509, "Gran Pompeya",
                "Catamarca"
        );
        Odontologo odontologo1 = new Odontologo(523124, "jorge", "lopez");
        Paciente paciente1 = new Paciente("jorge", "lopez junior", domicilio1, 12121212, LocalDate.of(2023, 7, 15),
                "jorgitojunior", "123"
        );
        Turno turno = new Turno(1, odontologo1, paciente1,
                LocalDate.of(2023,5, 15),
                LocalTime.of(15, 30, 0)
        );

        turnoService.guardarTurno(turno);
        Turno turnoBuscado = turnoService.buscar(turno.getId());

        Assertions.assertEquals(turno.getId(), turnoBuscado.getId());
    }
     */
}