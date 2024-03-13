package com.me.odontologo.controllers;

import com.me.odontologo.model.Domicilio;
import com.me.odontologo.services.IDomicilioService;
import com.me.odontologo.services.implementations.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("domicilios")
public class DomicilioController {
    private IDomicilioService domicilioService;

    @Autowired
    public DomicilioController(DomicilioService domicilioService){
        this.domicilioService = domicilioService;
    };

    @GetMapping("/all")
    public ResponseEntity<List<Domicilio>> getOdontologos() {
        ResponseEntity<List<Domicilio>> response;
        List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();
        if(!domicilios.isEmpty()){
            response = ResponseEntity.ok(domicilioService.buscarTodosLosDomicilios());
        }else{
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> buscarDomicilio(@PathVariable int id) {
        ResponseEntity<Domicilio> response;
        Domicilio domicilioBuscado = domicilioService.buscarDomicilio(id);
        if(domicilioBuscado != null){
            response = ResponseEntity.ok(domicilioBuscado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Domicilio> agregar(@RequestBody Domicilio domicilio){
        ResponseEntity<Domicilio> response;
        Domicilio domicilioAgregado = domicilioService.guardarDomicilio(domicilio);
        if(domicilioAgregado.getCalle() != null){
            response = ResponseEntity.ok(domicilioAgregado);
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable int id){
        ResponseEntity<HttpStatus> response;
        if(domicilioService.buscarDomicilio(id).getCalle() != null){
            domicilioService.eliminarDomicilio(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
