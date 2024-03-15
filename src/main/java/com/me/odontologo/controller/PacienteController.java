package com.me.odontologo.controller;

import com.me.odontologo.entity.Paciente;
import com.me.odontologo.service.IPacienteService;
import com.me.odontologo.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
