package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Turno;
import com.me.odontologo.exception.BadRequestException;
import com.me.odontologo.exception.NoContentException;
import com.me.odontologo.repository.ITurnoRepository;
import com.me.odontologo.service.IOdontologoService;
import com.me.odontologo.service.IPacienteService;
import com.me.odontologo.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                        IPacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Override
    public Turno guardarTurno(Turno turno){
        if (odontologoService.buscar(turno.getOdontologo().getId()).isPresent()
                 && pacienteService.buscar(turno.getPaciente().getId()) != null){
            return turnoRepository.save(turno);
        }else {
            throw new BadRequestException("El odontologo y/o el paciente no existe/n");
        }
    }

    @Override
    public Turno actualizarTurno(Turno turno){
        if (turnoRepository.findById(turno.getId()).isPresent()){
            return guardarTurno(turno);
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
    public List<Turno> buscarTodosLosTurnos(){
        List<Turno> turnos = turnoRepository.findAll();
        if (!turnos.isEmpty()){
            return turnos;
        }else{
            throw new NoContentException();
        }
    }

    @Override
    public Optional<Turno> buscar(Long id){
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            return turno;
        }else{
            throw new BadRequestException("No existe el turno con ese id");
        }
    }
}
