package com.me.odontologo.dao;

import com.me.odontologo.dao.implementations.TurnoDAOList;
import com.me.odontologo.domain.Domicilio;
import com.me.odontologo.domain.Odontologo;
import com.me.odontologo.domain.Paciente;
import com.me.odontologo.domain.Turno;
import com.me.odontologo.services.implementations.TurnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class TurnoDAOListTest {
    @Test
    public void buscarUno(){
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

        TurnoService turnoService = new TurnoService();
        turnoService.guardarTurno(turno);
        Turno turnoBuscado = turnoService.buscar(turno.getId());

        Assertions.assertEquals(turno.getId(), turnoBuscado.getId());
    }

    @Test
    public void buscarTodos(){
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

        TurnoService turnoService = new TurnoService();
        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(2, turnos.size());
    }
    @Test
    public void eliminarUno(){
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

        TurnoService turnoService = new TurnoService();
        turnoService.guardarTurno(turno1);
        turnoService.eliminarTurno(turno1.getId());
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();

        Assertions.assertEquals(0, turnos.size());
    }
    @Test
    public void guardar(){
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

        TurnoService turnoService = new TurnoService();
        turnoService.guardarTurno(turno1);
        Turno turnoBuscado = turnoService.buscar(turno1.getId());

        Assertions.assertEquals(turno1.getId(), turnoBuscado.getId());
    }
}