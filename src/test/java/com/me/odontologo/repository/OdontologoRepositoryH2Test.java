package com.me.odontologo.repository;

import com.me.odontologo.model.Odontologo;
import com.me.odontologo.services.implementations.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class OdontologoRepositoryH2Test {

    @Test
    public void guardarUno(){
        DB.crearTablas();

        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");

        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo1);

        Assertions.assertEquals(odontologo1.getMatricula(), odontologoGuardado.getMatricula());
    }

    @Test
    public void buscarTodos() {
        DB.crearTablas();
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
        Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        Assertions.assertEquals(2, odontologos.size());
    }

    @Test
    public void eliminarUno() {
        DB.crearTablas();
        // para que este test funcione
        // no se deben insertar datos manualmente en las tablas
        // desde el método "crearTablas" de la clase DB
        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
        Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologoAEliminar = odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.eliminarOdontologo(odontologoAEliminar.getId());

        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        Assertions.assertEquals(1, odontologos.size());
    }

    @Test
    public void buscarUno() {
        DB.crearTablas();

        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");

        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologoABuscar = odontologoService.guardarOdontologo(odontologo1);
        Odontologo odontologoBuscado = odontologoService.buscar(odontologoABuscar.getId());

        Assertions.assertEquals(odontologoABuscar.getId(), odontologoBuscado.getId());
    }
}