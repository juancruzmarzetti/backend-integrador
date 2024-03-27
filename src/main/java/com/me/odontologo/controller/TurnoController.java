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
        Turno turnoAgregado = turnoService.guardarTurno(turno);
        return ResponseEntity.ok(turnoAgregado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno){
        Turno turnoActualizado = turnoService.actualizarTurno(turno);
        return ResponseEntity.ok(turnoActualizado);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Turno>> getTurnos() {
        List<Turno> turnos = turnoService.buscarTodosLosTurnos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscar(id);
        return ResponseEntity.ok(turnoBuscado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
