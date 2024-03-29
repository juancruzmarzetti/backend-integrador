package com.me.odontologo.controller;

import com.me.odontologo.dto.request.TurnoRequestDTO;
import com.me.odontologo.dto.response.TurnoResponseDTO;
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
    public ResponseEntity<TurnoResponseDTO> agregar(@RequestBody TurnoRequestDTO turnoRequest){
        TurnoResponseDTO turnoAgregado = turnoService.guardarTurno(turnoRequest);
        return ResponseEntity.ok(turnoAgregado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoResponseDTO> actualizar(@RequestBody Turno turno){
        TurnoResponseDTO turnoActualizado = turnoService.actualizarTurno(turno);
        return ResponseEntity.ok(turnoActualizado);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TurnoResponseDTO>> getTurnos() {
        List<TurnoResponseDTO> turnos = turnoService.buscarTodosLosTurnos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> buscarTurno(@PathVariable Long id) {
        TurnoResponseDTO turnoBuscado = turnoService.buscar(id);
        return ResponseEntity.ok(turnoBuscado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
