package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Turno;
import com.me.odontologo.repository.ITurnoRepository;
import com.me.odontologo.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }
    @Override
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
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
