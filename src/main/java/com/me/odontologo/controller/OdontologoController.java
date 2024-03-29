package com.me.odontologo.controller;

import com.me.odontologo.dto.request.OdontologoRequestDTO;
import com.me.odontologo.dto.response.OdontologoResponseDTO;
import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.service.IOdontologoService;
import com.me.odontologo.service.implementation.OdontologoService;
// import org.springframework.stereotype.Controller;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<OdontologoResponseDTO>> getOdontologos() {
        List<OdontologoResponseDTO> odontologos = odontologoService.buscarTodosLosOdontologos();
        return ResponseEntity.ok(odontologos);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoResponseDTO> actualizarOdontologo(@RequestBody Odontologo odontologo){
        OdontologoResponseDTO odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok(odontologoActualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoResponseDTO> buscarOdontologo(@PathVariable Long id) {
        OdontologoResponseDTO odontologoBuscado = odontologoService.buscar(id);
        return ResponseEntity.ok(odontologoBuscado);
    }

    @PostMapping("/agregar")
    public ResponseEntity<OdontologoResponseDTO> agregar(@RequestBody OdontologoRequestDTO odontologoRequest){
        OdontologoResponseDTO odontologoAgregado = odontologoService.guardarOdontologo(odontologoRequest);
        return ResponseEntity.ok(odontologoAgregado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
