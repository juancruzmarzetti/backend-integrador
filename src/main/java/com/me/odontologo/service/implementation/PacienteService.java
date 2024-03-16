package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Paciente;
import com.me.odontologo.repository.IPacienteRepository;
import com.me.odontologo.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    @Override
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    @Override
    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteRepository.findAll();
    }
    @Override
    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }
}
