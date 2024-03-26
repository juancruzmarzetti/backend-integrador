package com.me.odontologo.controller;

import com.me.odontologo.entity.Paciente;
import com.me.odontologo.entity.Turno;
import com.me.odontologo.service.IOdontologoService;
import com.me.odontologo.service.IPacienteService;
import com.me.odontologo.service.ITurnoService;
import com.me.odontologo.service.implementation.OdontologoService;
import com.me.odontologo.service.implementation.PacienteService;
import com.me.odontologo.service.implementation.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Turno> agregar(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        Turno turnoAgregado = turnoService.guardarTurno(turno);
        if(turnoAgregado != null){
            response = ResponseEntity.ok(turnoAgregado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        Turno turnoActualizado = turnoService.actualizarTurno(turno);
        if(turnoActualizado != null){
            response = ResponseEntity.ok(turnoActualizado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Turno>> getTurnos() {
        ResponseEntity<List<Turno>> response;
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();
        if (!turnos.isEmpty()){
            response = ResponseEntity.ok(turnos);
        }else{
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarTurno(@PathVariable Long id) {
        ResponseEntity<Optional<Turno>> response;
        Optional<Turno> turnoBuscado = turnoService.buscar(id);
        if(turnoBuscado.isPresent()){
            response = ResponseEntity.ok(turnoBuscado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        ResponseEntity<HttpStatus> response;
        boolean siExiste = turnoService.eliminarTurno(id);
        if(siExiste){
            response = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
