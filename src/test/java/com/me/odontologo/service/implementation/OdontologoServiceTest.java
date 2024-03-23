package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.service.IOdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    void guardarOdontologo() {
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo1);

        Assertions.assertEquals(odontologo1.getMatricula(), odontologoGuardado.getMatricula());

        odontologoService.eliminarOdontologo(odontologoGuardado.getId());
    }

    @Test
    void eliminarOdontologo() {
        Odontologo odontologo1 = new Odontologo();
        Odontologo odontologo2 = new Odontologo();

        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        odontologo2.setMatricula(22222222);
        odontologo2.setNombre("Lionel");
        odontologo2.setApellido("Martinez");

        Odontologo odontologoAEliminar = odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.eliminarOdontologo(odontologoAEliminar.getId());

        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();

        Assertions.assertEquals(1, odontologos.size());

        odontologoService.eliminarOdontologo(odontologo2.getId());
    }

    @Test
    void buscarTodosLosOdontologos() {
        Odontologo odontologo1 = new Odontologo();
        Odontologo odontologo2 = new Odontologo();

        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        odontologo2.setMatricula(22222222);
        odontologo2.setNombre("Lionel");
        odontologo2.setApellido("Martinez");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();

        Assertions.assertEquals(2, odontologos.size());

        odontologoService.eliminarOdontologo(odontologo1.getId());
        odontologoService.eliminarOdontologo(odontologo2.getId());
    }

    @Test
    void buscar() {
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        Odontologo odontologoABuscar = odontologoService.guardarOdontologo(odontologo1);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(odontologoABuscar.getId());

        Assertions.assertTrue(odontologoBuscado.isPresent());

        odontologoService.eliminarOdontologo(odontologoABuscar.getId());
    }

}