package com.me.odontologo.service.implementation;

import com.me.odontologo.dto.request.PacienteRequestDTO;
import com.me.odontologo.dto.response.PacienteResponseDTO;
import com.me.odontologo.entity.Domicilio;
import com.me.odontologo.entity.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    void guardarPaciente() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setNumero(111);
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Buenos Aires");

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12312356);
        paciente1.setFechaDeAlta(LocalDate.of(2024,7,10));
        paciente1.setUsuario("jorge123");
        paciente1.setPassword("123456789");

        Optional<PacienteResponseDTO> pacienteAlGuardar = pacienteService.guardarPaciente(paciente1);

        Assertions.assertTrue(pacienteAlGuardar.isPresent());
        pacienteService.eliminarPaciente(pacienteAlGuardar.get().getId());
    }

    @Test
    void eliminarPaciente() {
        Domicilio domicilio1 =  new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Buenos Aires");
        domicilio1.setNumero(111);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12312356);
        paciente1.setFechaDeAlta(LocalDate.of(2024,7,10));
        paciente1.setUsuario("jorge123");
        paciente1.setPassword("123456789");

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Buenos Aires2");
        domicilio2.setNumero(222);

        PacienteRequestDTO paciente2 = new PacienteRequestDTO();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(12312352);
        paciente2.setFechaDeAlta(LocalDate.of(2022,2,22));
        paciente2.setUsuario("jorge222");
        paciente2.setPassword("123456222");

        Optional<PacienteResponseDTO> paciente1Resp = pacienteService.guardarPaciente(paciente1);
        Optional<PacienteResponseDTO> paciente2Resp = pacienteService.guardarPaciente(paciente2);

        pacienteService.eliminarPaciente(paciente1Resp.get().getId());
        List<PacienteResponseDTO> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(1, pacientes.size());

        pacienteService.eliminarPaciente(paciente2Resp.get().getId());
    }

    @Test
    void buscarTodosLosPacientes() {
        Domicilio domicilio1 =  new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Buenos Aires");
        domicilio1.setNumero(111);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12312356);
        paciente1.setFechaDeAlta(LocalDate.of(2024,7,10));
        paciente1.setUsuario("jorge123");
        paciente1.setPassword("123456789");

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Pompeya2");
        domicilio2.setLocalidad("Gran Pompeya2");
        domicilio2.setProvincia("Buenos Aires2");
        domicilio2.setNumero(222);

        PacienteRequestDTO paciente2 = new PacienteRequestDTO();
        paciente2.setNombre("Jorge2");
        paciente2.setApellido("Lopez2");
        paciente2.setDomicilio(domicilio2);
        paciente2.setDni(12312352);
        paciente2.setFechaDeAlta(LocalDate.of(2022,2,22));
        paciente2.setUsuario("jorge222");
        paciente2.setPassword("123456222");

        Optional<PacienteResponseDTO> paciente1Resp = pacienteService.guardarPaciente(paciente1);
        Optional<PacienteResponseDTO> paciente2Resp = pacienteService.guardarPaciente(paciente2);
        List<PacienteResponseDTO> pacientes = pacienteService.buscarTodosLosPacientes();

        Assertions.assertEquals(2, pacientes.size());
        pacienteService.eliminarPaciente(paciente1Resp.get().getId());
        pacienteService.eliminarPaciente(paciente2Resp.get().getId());
    }

    @Test
    void buscar() {
        Domicilio domicilio1 =  new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Buenos Aires");
        domicilio1.setNumero(111);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12312356);
        paciente1.setFechaDeAlta(LocalDate.of(2024,7,10));
        paciente1.setUsuario("jorge123");
        paciente1.setPassword("123456789");

        Optional<PacienteResponseDTO> paciente1Resp = pacienteService.guardarPaciente(paciente1);
        PacienteResponseDTO pacienteBuscado = pacienteService.buscar(paciente1Resp.get().getId());

        Assertions.assertEquals(paciente1Resp.get().getId(), pacienteBuscado.getId());
        pacienteService.eliminarPaciente(paciente1Resp.get().getId());
    }

    @Test
    void actualizar() {
        Domicilio domicilio1 =  new Domicilio();
        domicilio1.setCalle("Pompeya");
        domicilio1.setLocalidad("Gran Pompeya");
        domicilio1.setProvincia("Buenos Aires");
        domicilio1.setNumero(111);

        PacienteRequestDTO paciente1 = new PacienteRequestDTO();
        paciente1.setNombre("Jorge");
        paciente1.setApellido("Lopez");
        paciente1.setDomicilio(domicilio1);
        paciente1.setDni(12312356);
        paciente1.setFechaDeAlta(LocalDate.of(2024,7,10));
        paciente1.setUsuario("jorge123");
        paciente1.setPassword("123456789");

        Optional<PacienteResponseDTO> paciente1Resp = pacienteService.guardarPaciente(paciente1);

        Paciente pacienteActualizar = new Paciente();
        pacienteActualizar.setId(paciente1Resp.get().getId());
        pacienteActualizar.setNombre(paciente1Resp.get().getNombre());
        pacienteActualizar.setApellido(paciente1Resp.get().getApellido());
        pacienteActualizar.setDomicilio(paciente1Resp.get().getDomicilio());
        pacienteActualizar.setDni(paciente1Resp.get().getDni());
        pacienteActualizar.setFechaDeAlta(paciente1Resp.get().getFechaDeAlta());
        pacienteActualizar.setUsuario("jorge222");
        pacienteActualizar.setPassword("123456789");

        PacienteResponseDTO pacienteActualizado = pacienteService.actualizarPaciente(pacienteActualizar);

        Assertions.assertEquals("jorge222", pacienteActualizado.getUsuario());

        pacienteService.eliminarPaciente(paciente1Resp.get().getId());
    }
}