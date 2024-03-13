package com.me.odontologo.controllers;

import com.me.odontologo.model.Turno;
import com.me.odontologo.services.IOdontologoService;
import com.me.odontologo.services.IPacienteService;
import com.me.odontologo.services.ITurnoService;
import com.me.odontologo.services.implementations.OdontologoService;
import com.me.odontologo.services.implementations.PacienteService;
import com.me.odontologo.services.implementations.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("turnos")
public class TurnoController {
    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;
    
    @Autowired
    public TurnoController(PacienteService pacienteService,
                           OdontologoService odontologoService,
                           TurnoService turnoService){
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.turnoService = turnoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Turno> agregar(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        if (odontologoService.buscar(turno.getOdontologo().getId()) != null &&
                pacienteService.buscar(turno.getPaciente().getId()) != null) {

            response = ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
