package com.me.odontologo.controller;

import com.me.odontologo.services.OdontologoService;
import com.me.odontologo.domain.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping
    public List<Odontologo> getOdontologos() {
        return odontologoService.listaOdontologos();
    }
}
