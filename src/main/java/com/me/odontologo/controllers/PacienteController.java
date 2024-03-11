package com.me.odontologo.controllers;

import com.me.odontologo.model.Odontologo;
import com.me.odontologo.model.Paciente;
import com.me.odontologo.services.IDomicilioService;
import com.me.odontologo.services.IPacienteService;
import com.me.odontologo.services.implementations.DomicilioService;
import com.me.odontologo.services.implementations.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("pacientes")
public class PacienteController {
    private IPacienteService pacienteService;
    private IDomicilioService domicilioService;

    public PacienteController(PacienteService pacienteService, DomicilioService domicilioService) {
        this.pacienteService = pacienteService;
        this.domicilioService = domicilioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> getOdontologos() {
        return ResponseEntity.ok(pacienteService.buscarTodosLosPacientes());
    }
    //TODO
}
