package com.me.odontologo.controllers;

import com.me.odontologo.model.Odontologo;
import com.me.odontologo.services.IOdontologoService;
import com.me.odontologo.services.implementations.OdontologoService;
// import org.springframework.stereotype.Controller;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Odontologo>> getOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodosLosOdontologos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable int id) {
        ResponseEntity<Odontologo> response;
        Odontologo odontologoBuscado = odontologoService.buscar(id);
        if(odontologoBuscado != null){
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
        if(odontologoAgregado.getNombre() != null){
            response = ResponseEntity.ok(odontologoAgregado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable int id){
        ResponseEntity<HttpStatus> response;
        if(odontologoService.buscar(id).getNombre() != null){
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
