package com.me.odontologo.services.implementations;

import com.me.odontologo.dao.DB;
import com.me.odontologo.model.Domicilio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DomicilioServiceTest {
    @Autowired
    private DomicilioService domicilioService;
    @BeforeEach
    void setUp() {
        DB.crearTablas();
    }

    @Test
    void guardarDomicilio() {
        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");

        Domicilio domicilioGuardado = domicilioService.guardarDomicilio(domicilio1);

        Assertions.assertEquals(domicilio1.getNumero(), domicilioGuardado.getNumero());
    }

    @Test
    void buscarTodosLosDomicilios() {
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");
        Domicilio domicilio2 = new Domicilio("Av. de Mayo", 5678, "La Plata", "Buenos Aires");

        domicilioService.guardarDomicilio(domicilio1);
        domicilioService.guardarDomicilio(domicilio2);

        List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();
        Assertions.assertEquals(2, domicilios.size());
    }

    @Test
    void eliminarDomicilio() {
        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");
        Domicilio domicilio2 = new Domicilio("Av. de Mayo", 5678, "La Plata", "Buenos Aires");

        Domicilio domicilioAEliminar = domicilioService.guardarDomicilio(domicilio1);
        domicilioService.guardarDomicilio(domicilio2);
        domicilioService.eliminarDomicilio(domicilioAEliminar.getId());
        List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();

        Assertions.assertEquals(1, domicilios.size());
    }

    @Test
    void buscarDomicilio() {
        DB.crearTablas();

        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");

        Domicilio domicilioABuscar = domicilioService.guardarDomicilio(domicilio1);
        Domicilio domicilioBuscado = domicilioService.buscarDomicilio(domicilioABuscar.getId());

        Assertions.assertEquals(domicilioABuscar.getId(), domicilioBuscado.getId());
    }
}