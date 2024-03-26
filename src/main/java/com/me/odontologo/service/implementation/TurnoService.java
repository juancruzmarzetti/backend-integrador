package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Turno;
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
        Turno turnoGuardado = null;
        if (odontologoService.buscar(turno.getOdontologo().getId()).isPresent()
                 && pacienteService.buscar(turno.getPaciente().getId()) != null){
            turnoGuardado = turnoRepository.save(turno);
        }
        return turnoGuardado;
    }

    @Override
    public Turno actualizarTurno(Turno turno){
        Turno turnoActualizado = null;
        if (turnoRepository.findById(turno.getId()).isPresent()){
            turnoActualizado = turnoRepository.save(turno);
        }
        return turnoActualizado;
    }
    @Override
    public boolean eliminarTurno(Long id){
        boolean response = true;
        if (turnoRepository.findById(id).isPresent()){
            turnoRepository.deleteById(id);
        }else{
            response = false;
        }
        return response;
    }
    @Override
    public List<Turno> buscarTodosLosTurnos(){
        return turnoRepository.findAll();
    }
    @Override
    public Optional<Turno> buscar(Long id){
        return turnoRepository.findById(id);
    }
}
