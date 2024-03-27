package com.me.odontologo.controller;

import com.me.odontologo.dto.PacienteResponseDTO;
import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.service.IPacienteService;
import com.me.odontologo.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes() {
        List<PacienteResponseDTO> pacientes = pacienteService.buscarTodosLosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPaciente(@PathVariable Long id) {
        PacienteResponseDTO pacienteBuscado = pacienteService.buscar(id);
        return ResponseEntity.ok(pacienteBuscado);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Optional<PacienteResponseDTO>> agregar(@RequestBody Paciente paciente){
        Optional<PacienteResponseDTO> pacienteAgregado = pacienteService.guardarPaciente(paciente);
        return ResponseEntity.ok(pacienteAgregado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteResponseDTO> actualizar(@RequestBody Paciente paciente){
        PacienteResponseDTO pacienteActualizado = pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok(pacienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
