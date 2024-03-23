package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Domicilio;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.repository.IDomicilioRepository;
import com.me.odontologo.repository.IPacienteRepository;
import com.me.odontologo.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;
    private IDomicilioRepository domicilioRepository;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Optional<Paciente> guardarPaciente(Paciente paciente){
        //TODO parsear fecha String del JSON a fecha LocalDate
        pacienteRepository.save(paciente);
        return pacienteRepository.findById(paciente.getId());
    }

    @Override
    public Optional<Paciente> actualizarPaciente(Paciente paciente){
        Optional<Paciente> pacienteActualizado;
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            pacienteRepository.save(paciente);
            pacienteActualizado = pacienteRepository.findById(paciente.getId());
        }else{
            return null;
        }
        return pacienteActualizado;
    }
    @Override
    public boolean eliminarPaciente(Long id){
        boolean response = true;
        if(pacienteRepository.findById(id).isPresent()){
            pacienteRepository.deleteById(id);
        }else {
            response = false;
        }
        return response;
    }
    @Override
    public List<Paciente> buscarTodosLosPacientes(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        if(!pacientes.isEmpty()){
            return pacientes;
        }else {
            return null;
        }
    }
    @Override
    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }
}
