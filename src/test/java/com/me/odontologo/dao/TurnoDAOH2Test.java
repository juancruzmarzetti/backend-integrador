package com.me.odontologo.dao;

import com.me.odontologo.domain.Turno;
import com.me.odontologo.services.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class TurnoDAOH2Test {
    @Test
    public void guardarYBuscarTodos(){
        Turno turno1 = new Turno(
                1,
                LocalDate.of(2011, 11,11),
                LocalTime.of(11,11,11)
        );
        Turno turno2 = new Turno(
                2,
                LocalDate.of(2022, 2,22),
                LocalTime.of(22,22,22)
        );
        TurnoService turnoService = new TurnoService(new TurnoDAOH2());
        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();
        Assertions.assertEquals(2, turnos.size());
    }

    @Test
    public void eliminarUno(){
        Turno turno1 = new Turno(
                1,
                LocalDate.of(2011, 11,11),
                LocalTime.of(11,11,11)
        );
        Turno turno2 = new Turno(
                2,
                LocalDate.of(2022, 2,22),
                LocalTime.of(22,22,22)
        );
        TurnoService turnoService = new TurnoService(new TurnoDAOH2());
        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        turnoService.eliminarTurno(turno2.getId());
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();
        Assertions.assertEquals(1, turnos.size());
    }

    @Test
    public void buscarUno(){
        Turno turno1 = new Turno(
                1,
                LocalDate.of(2011, 11,11),
                LocalTime.of(11,11,11)
        );
        Turno turno2 = new Turno(
                2,
                LocalDate.of(2022, 2,22),
                LocalTime.of(22,22,22)
        );
        TurnoService turnoService = new TurnoService(new TurnoDAOH2());
        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        Assertions.assertEquals(1, turnoService.buscar(turno1.getId()).getId());
    }
}