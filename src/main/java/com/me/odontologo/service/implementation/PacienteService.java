package com.me.odontologo.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.odontologo.dto.PacienteResponseDTO;
import com.me.odontologo.entity.Domicilio;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.exception.BadRequestException;
import com.me.odontologo.exception.NoContentException;
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
        PacienteResponseDTO pacienteDTO;
        if (paciente.getUsuario() != null && paciente.getDomicilio() != null
        && paciente.getDni() != null && paciente.getFechaDeAlta() != null){
            pacienteDTO = mapper.convertValue(
                    pacienteRepository.save(paciente), PacienteResponseDTO.class);
            return Optional.of(pacienteDTO);
        }else{
            throw new BadRequestException("Faltan rellenar los campos fundamentales");
        }
    }

    @Override
    public PacienteResponseDTO actualizarPaciente(Paciente paciente){
        PacienteResponseDTO pacienteDTOActualizado = null;
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            Paciente pacienteActualizado = pacienteRepository.save(paciente);
            pacienteDTOActualizado =
                    mapper.convertValue(pacienteActualizado, PacienteResponseDTO.class);
            return pacienteDTOActualizado;
        }else{
            throw new BadRequestException("El paciente no existe");
        }
    }
    @Override
    public void eliminarPaciente(Long id){
        if(pacienteRepository.findById(id).isPresent()){
            pacienteRepository.deleteById(id);
        }else {
            throw new BadRequestException("El paciente no se pudo eliminar porque no existe");
        }
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
        }else{
            throw new NoContentException();
        }
    }
    @Override
    public PacienteResponseDTO buscar(Long id){
        PacienteResponseDTO pacienteDTO;
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if (pacienteBuscado.isPresent()){
            pacienteDTO = mapper.convertValue(pacienteBuscado, PacienteResponseDTO.class);
            return pacienteDTO;
        }else{
            throw new BadRequestException("El paciente no existe");
        }
    }
}
