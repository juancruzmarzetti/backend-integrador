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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private ITurnoRepository turnoRepository;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository,
                        IOdontologoService odontologoService,
                        IPacienteService pacienteService,
                        ObjectMapper mapper) {
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
            throw new BadRequestException("El odontologo y/o el paciente no existe/n");
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
                throw new BadRequestException("El odontologo y/o el paciente" +
                        "que enviaste en el cuerpo de actualización" +
                        "no existe/n");
            }
        }else{
            throw new BadRequestException("El turno no existe");
        }
    }
    @Override
    public void eliminarTurno(Long id){
        if (turnoRepository.findById(id).isPresent()){
            turnoRepository.deleteById(id);
        }else{
            throw new BadRequestException("El turno no existe");
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
            throw new BadRequestException("No existe el turno con ese id");
        }
    }
}
