package com.me.odontologo.dao;

import com.me.odontologo.dao.implementations.DomicilioDAOH2;
import com.me.odontologo.domain.Domicilio;
import com.me.odontologo.services.DomicilioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DomicilioDAOH2Test {
    @Test
    public void guardarUno(){
        DB.crearTablas();

        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");

        DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
        Domicilio domicilioGuardado = domicilioService.guardarDomicilio(domicilio1);

        Assertions.assertEquals(domicilio1.getNumero(), domicilioGuardado.getNumero());
    }
    @Test
    public void buscarTodos() {
        DB.crearTablas();
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");
        Domicilio domicilio2 = new Domicilio("Av. de Mayo", 5678, "La Plata", "Buenos Aires");

        DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
        domicilioService.guardarDomicilio(domicilio1);
        domicilioService.guardarDomicilio(domicilio2);

        List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();
        Assertions.assertEquals(2, domicilios.size());
    }

    @Test
    public void eliminarUno() {
        DB.crearTablas();
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");
        Domicilio domicilio2 = new Domicilio("Av. de Mayo", 5678, "La Plata", "Buenos Aires");

        DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
        Domicilio domicilioAEliminar = domicilioService.guardarDomicilio(domicilio1);
        domicilioService.guardarDomicilio(domicilio2);
        domicilioService.eliminarDomicilio(domicilioAEliminar.getId());
        List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();

        Assertions.assertEquals(1, domicilios.size());
    }

    @Test
    public void buscarUno() {
        DB.crearTablas();

        Domicilio domicilio1 = new Domicilio("Av. Rivadavia", 1234, "Buenos Aires", "CABA");

        DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
        Domicilio domicilioABuscar = domicilioService.guardarDomicilio(domicilio1);
        Domicilio domicilioBuscado = domicilioService.buscarDomicilio(domicilioABuscar.getId());

        Assertions.assertEquals(domicilioABuscar.getId(), domicilioBuscado.getId());
    }
}
