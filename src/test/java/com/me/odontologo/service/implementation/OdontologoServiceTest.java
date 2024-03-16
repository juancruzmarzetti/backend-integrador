package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Odontologo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OdontologoServiceTest {
    /*
    @Autowired
    private OdontologoService odontologoService;

    @BeforeAll
    public static void setUp() {
        Odontologo odontologo1 = new Odontologo();
        Odontologo odontologo2 = new Odontologo();
    }

    @Test
    void guardarOdontologo() {
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo1);
        //Assertions.assertEquals();
    }

    @Test
    void eliminarOdontologo() {
        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
        Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

        Odontologo odontologoAEliminar = odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.eliminarOdontologo(odontologoAEliminar.getId());

        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        Assertions.assertEquals(1, odontologos.size());
    }

    @Test
    void buscarTodosLosOdontologos() {
        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
        Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        Assertions.assertEquals(2, odontologos.size());
    }

    @Test
    void buscar() {
        Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");

        Odontologo odontologoABuscar = odontologoService.guardarOdontologo(odontologo1);
        Odontologo odontologoBuscado = odontologoService.buscar(odontologoABuscar.getId());

        Assertions.assertEquals(odontologoABuscar.getId(), odontologoBuscado.getId());
    }
     */
}