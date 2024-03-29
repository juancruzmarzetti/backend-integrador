package com.me.odontologo.service.implementation;

import com.me.odontologo.dto.request.OdontologoRequestDTO;
import com.me.odontologo.dto.response.OdontologoResponseDTO;
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
        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        OdontologoResponseDTO odontologoGuardado = odontologoService.guardarOdontologo(odontologo1);

        Assertions.assertEquals(odontologo1.getMatricula(), odontologoGuardado.getMatricula());

        odontologoService.eliminarOdontologo(odontologoGuardado.getId());
    }

    @Test
    void eliminarOdontologo() {
        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        OdontologoRequestDTO odontologo2 = new OdontologoRequestDTO();

        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        odontologo2.setMatricula(22222222);
        odontologo2.setNombre("Lionel");
        odontologo2.setApellido("Martinez");

        OdontologoResponseDTO odontologoAEliminar = odontologoService.guardarOdontologo(odontologo1);
        OdontologoResponseDTO odontologo2Resp =  odontologoService.guardarOdontologo(odontologo2);
        odontologoService.eliminarOdontologo(odontologoAEliminar.getId());

        List<OdontologoResponseDTO> odontologos = odontologoService.buscarTodosLosOdontologos();

        Assertions.assertEquals(1, odontologos.size());

        odontologoService.eliminarOdontologo(odontologo2Resp.getId());
    }

    @Test
    void buscarTodosLosOdontologos() {
        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        OdontologoRequestDTO odontologo2 = new OdontologoRequestDTO();

        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        odontologo2.setMatricula(22222222);
        odontologo2.setNombre("Lionel");
        odontologo2.setApellido("Martinez");

        OdontologoResponseDTO odontologo1Resp = odontologoService.guardarOdontologo(odontologo1);
        OdontologoResponseDTO odontologo2Resp = odontologoService.guardarOdontologo(odontologo2);

        List<OdontologoResponseDTO> odontologos = odontologoService.buscarTodosLosOdontologos();

        Assertions.assertEquals(2, odontologos.size());

        odontologoService.eliminarOdontologo(odontologo1Resp.getId());
        odontologoService.eliminarOdontologo(odontologo2Resp.getId());
    }

    @Test
    void buscar() {
        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        OdontologoResponseDTO odontologoABuscar = odontologoService.guardarOdontologo(odontologo1);
        OdontologoResponseDTO odontologoBuscado = odontologoService.buscar(odontologoABuscar.getId());

        Assertions.assertEquals(odontologoABuscar.getMatricula(), odontologoBuscado.getMatricula());

        odontologoService.eliminarOdontologo(odontologoABuscar.getId());
    }

    @Test
    void actualizar() {
        OdontologoRequestDTO odontologo1 = new OdontologoRequestDTO();
        odontologo1.setMatricula(1111111);
        odontologo1.setNombre("Jorge");
        odontologo1.setApellido("Messi");

        OdontologoResponseDTO odontologoGuardado = odontologoService.guardarOdontologo(odontologo1);

        Odontologo odontologoActualizar = new Odontologo();
        odontologoActualizar.setId(odontologoGuardado.getId());
        odontologoActualizar.setMatricula(2222222);
        odontologoActualizar.setNombre(odontologoGuardado.getNombre());
        odontologoActualizar.setApellido(odontologoGuardado.getApellido());


        OdontologoResponseDTO odontologoActualizado = odontologoService.actualizarOdontologo(odontologoActualizar);

        Assertions.assertEquals(2222222, odontologoActualizado.getMatricula());

        odontologoService.eliminarOdontologo(odontologoGuardado.getId());
    }
}