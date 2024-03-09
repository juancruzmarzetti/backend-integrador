package com.me.odontologo.controller;

import com.me.odontologo.model.Odontologo;
import com.me.odontologo.services.IOdontologoService;
import com.me.odontologo.services.implementations.OdontologoService;
// import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {
    //TODO
    private IOdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping
    public List<Odontologo> getOdontologos() {
        //return odontologoService.listaOdontologos(); //TODO
        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable int id) {
        return ResponseEntity.ok(odontologoService.buscar(id));
    }
}
