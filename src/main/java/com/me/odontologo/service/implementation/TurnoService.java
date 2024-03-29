package com.me.odontologo.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.odontologo.dto.request.TurnoRequestDTO;
import com.me.odontologo.dto.response.TurnoResponseDTO;
import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.entity.Turno;
import com.me.odontologo.exception.BadRequestException;
import com.me.odontologo.exception.NoContentException;
import com.me.odontologo.repository.ITurnoRepository;
import com.me.odontologo.service.IOdontologoService;
import com.me.odontologo.service.IPacienteService;
import com.me.odontologo.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final ITurnoRepository turnoRepository;
    private final IOdontologoService odontologoService;
    private final IPacienteService pacienteService;
    private final Logger LOGGER = Logger.getLogger(TurnoService.class);

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository,
                        IOdontologoService odontologoService,
                        IPacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Override
    public TurnoResponseDTO guardarTurno(TurnoRequestDTO turnoRequest){
        Long odontologoId = odontologoService.buscar(turnoRequest.getOdontologo()).getId();
        Long pacienteId = pacienteService.buscar(turnoRequest.getPaciente()).getId();
        if (odontologoId.equals(turnoRequest.getOdontologo())
                 && pacienteId.equals(turnoRequest.getPaciente())){

            Paciente paciente = new Paciente();
            paciente.setId(pacienteId);

            Odontologo odontologo = new Odontologo();
            odontologo.setId(odontologoId);

            Turno turnoAGuardar = new Turno();
            turnoAGuardar.setOdontologo(odontologo);
            turnoAGuardar.setPaciente(paciente);
            turnoAGuardar.setFecha(turnoRequest.getFecha());
            turnoAGuardar.setHora(turnoRequest.getHora());

            turnoRepository.save(turnoAGuardar);

            TurnoResponseDTO turnoResponse = new TurnoResponseDTO(
                    turnoAGuardar.getId(),
                    turnoAGuardar.getOdontologo().getId(),
                    turnoAGuardar.getPaciente().getId(),
                    turnoAGuardar.getFecha(),
                    turnoAGuardar.getHora()
                    );

            return turnoResponse;
        }else {
            LOGGER.error("El odontologo con id" + odontologoId +
                    " y/o el paciente con id" + pacienteId +
                    " no existe/n");
            throw new BadRequestException("El odontologo con id" + odontologoId +
                    " y/o el paciente con id" + pacienteId +
                    " no existe/n");
        }
    }

    @Override
    public TurnoResponseDTO actualizarTurno(Turno turno){
        if (turnoRepository.findById(turno.getId()).isPresent()){
            if(pacienteService.buscar(turno.getPaciente().getId()) != null
            && odontologoService.buscar(turno.getOdontologo().getId()) != null) {
                turnoRepository.save(turno);

                TurnoResponseDTO turnoResponse = new TurnoResponseDTO(
                        turno.getId(),
                        turno.getOdontologo().getId(),
                        turno.getPaciente().getId(),
                        turno.getFecha(),
                        turno.getHora()
                );
                return turnoResponse;
            }else{
                LOGGER.error(
                        "El odontologo con id " + turno.getOdontologo().getId() +
                        " y/o el paciente con id " + turno.getPaciente().getId() +
                        "que enviaste en el cuerpo de actualización no existe/n"
                );
                throw new BadRequestException(
                        "El odontologo con id " + turno.getOdontologo().getId() +
                        " y/o el paciente con id " + turno.getPaciente().getId() +
                        "que enviaste en el cuerpo de actualización no existe/n"
                );
            }
        }else{
            LOGGER.error("El turno con id " + turno.getId() + " no existe");
            throw new BadRequestException("El turno con id " + turno.getId() +
                    " no existe");
        }
    }
    @Override
    public void eliminarTurno(Long id){
        if (turnoRepository.findById(id).isPresent()){
            turnoRepository.deleteById(id);
        }else{
            LOGGER.error("El turno con id " + id + " no existe");
            throw new BadRequestException("El turno con id " + id + " no existe");
        }
    }
    @Override
    public List<TurnoResponseDTO> buscarTodosLosTurnos(){
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDTO> turnosResponse = new ArrayList<>();
        if (!turnos.isEmpty()){
            for(Turno t: turnos){
                TurnoResponseDTO turnoRes = new TurnoResponseDTO(
                        t.getId(),
                        t.getOdontologo().getId(),
                        t.getPaciente().getId(),
                        t.getFecha(),
                        t.getHora()
                );
                turnosResponse.add(turnoRes);
            }
            return turnosResponse;
        }else{
            LOGGER.error("la petición se realizó con éxito pero" +
                    " la respuesta no tiene contenido");
            throw new NoContentException();
        }
    }

    @Override
    public TurnoResponseDTO buscar(Long id){
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            TurnoResponseDTO turnoRes = new TurnoResponseDTO(
                    turno.get().getId(),
                    turno.get().getOdontologo().getId(),
                    turno.get().getPaciente().getId(),
                    turno.get().getFecha(),
                    turno.get().getHora()
            );
            return turnoRes;
        }else{
            LOGGER.error("No existe el turno con id " + id);
            throw new BadRequestException("No existe el turno con id " + id);
        }
    }
}
