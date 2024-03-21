package com.me.odontologo.controller;

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
    public ResponseEntity<List<Odontologo>> getOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodosLosOdontologos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologo(@PathVariable Long id) {
        ResponseEntity<Optional<Odontologo>> response;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(id);
        if(odontologoBuscado.isPresent()){
            response = ResponseEntity.ok(odontologoBuscado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Odontologo> agregar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response;
        Odontologo odontologoAgregado = odontologoService.guardarOdontologo(odontologo);
        if(odontologoAgregado != null){
            response = ResponseEntity.ok(odontologoAgregado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id){
        ResponseEntity<HttpStatus> response;
        if(odontologoService.buscar(id).isPresent()){
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
