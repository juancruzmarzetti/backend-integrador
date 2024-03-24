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
        ResponseEntity<List<PacienteResponseDTO>> response;
        List<PacienteResponseDTO> pacientes = pacienteService.buscarTodosLosPacientes();
        if(!pacientes.isEmpty()){
            response = ResponseEntity.ok(pacienteService.buscarTodosLosPacientes());
        }else {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPaciente(@PathVariable Long id) {
        ResponseEntity<Optional<Paciente>> response;
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(id);
        if(pacienteBuscado.isPresent()){
            response = ResponseEntity.ok(pacienteBuscado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Optional<PacienteResponseDTO>> agregar(@RequestBody Paciente paciente){
        ResponseEntity<Optional<PacienteResponseDTO>> response;
        Optional<PacienteResponseDTO> pacienteAgregado = pacienteService.guardarPaciente(paciente);
        if(pacienteAgregado.isPresent()){
            response = ResponseEntity.ok(pacienteAgregado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Optional<PacienteResponseDTO>> actualizar(@RequestBody Paciente paciente){
        ResponseEntity<Optional<PacienteResponseDTO>> response;
        Optional<PacienteResponseDTO> pacienteActualizado = pacienteService.actualizarPaciente(paciente);
        if(pacienteActualizado.isPresent()){
            response = ResponseEntity.ok(pacienteActualizado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        ResponseEntity<HttpStatus> response;
        boolean siExiste = pacienteService.eliminarPaciente(id);
        if(siExiste){
            response = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
