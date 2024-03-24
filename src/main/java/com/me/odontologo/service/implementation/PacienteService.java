package com.me.odontologo.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.odontologo.dto.PacienteResponseDTO;
import com.me.odontologo.entity.Domicilio;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.repository.IDomicilioRepository;
import com.me.odontologo.repository.IPacienteRepository;
import com.me.odontologo.service.IPacienteService;
import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final IPacienteRepository pacienteRepository;
    private final ObjectMapper mapper;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<PacienteResponseDTO> guardarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
        PacienteResponseDTO pacienteDTO = mapper.convertValue(paciente, PacienteResponseDTO.class);
        return Optional.of(pacienteDTO);
    }

    @Override
    public Optional<PacienteResponseDTO> actualizarPaciente(Paciente paciente){
        Optional<PacienteResponseDTO> pacienteDTOOptionalActualizado;
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            Paciente pacienteActualizado = pacienteRepository.save(paciente);
            PacienteResponseDTO pacienteDTO =
                    mapper.convertValue(pacienteActualizado, PacienteResponseDTO.class);
            pacienteDTOOptionalActualizado = Optional.of(pacienteDTO);
        }else{
            return null;
        }
        return pacienteDTOOptionalActualizado;
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
    public List<PacienteResponseDTO> buscarTodosLosPacientes(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteResponseDTO> pacientesDTO = new ArrayList<>();
        if(!pacientes.isEmpty()){
            for(Paciente p: pacientes){
                pacientesDTO.add(mapper.convertValue(p, PacienteResponseDTO.class));
            }
            return pacientesDTO;
        }else {
            return null;
        }
    }
    @Override
    public Optional<PacienteResponseDTO> buscar(Long id){
        PacienteResponseDTO pacienteDTO =
                mapper.convertValue(pacienteRepository.findById(id), PacienteResponseDTO.class);
        return Optional.of(pacienteDTO);
    }
}
