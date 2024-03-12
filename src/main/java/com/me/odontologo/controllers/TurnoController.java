package com.me.odontologo.controllers;

import com.me.odontologo.model.Turno;
import com.me.odontologo.services.IOdontologoService;
import com.me.odontologo.services.IPacienteService;
import com.me.odontologo.services.ITurnoService;
import com.me.odontologo.services.implementations.OdontologoService;
import com.me.odontologo.services.implementations.PacienteService;
import com.me.odontologo.services.implementations.TurnoService;
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
        if(){

        }

        return response;
    }
}
