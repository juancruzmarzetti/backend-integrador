package com.me.odontologo.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.odontologo.dto.request.PacienteRequestDTO;
import com.me.odontologo.dto.response.PacienteResponseDTO;
import com.me.odontologo.entity.Paciente;
import com.me.odontologo.exception.BadRequestException;
import com.me.odontologo.exception.NoContentException;
import com.me.odontologo.repository.IPacienteRepository;
import com.me.odontologo.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final IPacienteRepository pacienteRepository;
    private final ObjectMapper mapper;
    private final Logger LOGGER = Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<PacienteResponseDTO> guardarPaciente(PacienteRequestDTO pacienteRequest){
        if (pacienteRequest.getDomicilio() != null
        && pacienteRequest.getDni() != null && pacienteRequest.getFechaDeAlta() != null){
            Paciente paciente = mapper.convertValue(pacienteRequest, Paciente.class);
            pacienteRepository.save(paciente);
            return Optional.of(mapper.convertValue(paciente, PacienteResponseDTO.class));
        }else{
            LOGGER.error("Faltan rellenar los campos fundamentales, " +
                    "pueden ser alguno de los siguientes: " +
                    "domicilio, dni o fechaDeAlta");
            throw new BadRequestException("Faltan rellenar los campos fundamentales, " +
                    "pueden ser alguno de los siguientes: " +
                    "domicilio, dni o fechaDeAlta");
        }
    }

    @Override
    public PacienteResponseDTO actualizarPaciente(Paciente paciente){
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            pacienteRepository.save(paciente);
            return mapper.convertValue(paciente, PacienteResponseDTO.class);
        }else{
            LOGGER.error("El paciente con id " +
                    paciente.getId() + " no existe");
            throw new BadRequestException("El paciente con id " +
                    paciente.getId() + " no existe");
        }
    }
    @Override
    public void eliminarPaciente(Long id){
        if(pacienteRepository.findById(id).isPresent()){
            pacienteRepository.deleteById(id);
        }else {
            LOGGER.error("El paciente con id " + id +
                    " no se pudo eliminar porque no existe");
            throw new BadRequestException("El paciente con id " + id +
                    " no se pudo eliminar porque no existe");
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
            LOGGER.error("la petición se realizó con éxito pero" +
                    " la respuesta no tiene contenido");
            throw new NoContentException();
        }
    }
    @Override
    public PacienteResponseDTO buscar(Long id){
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if (pacienteBuscado.isPresent()){
            return mapper.convertValue(pacienteBuscado, PacienteResponseDTO.class);
        }else{
            LOGGER.error("El paciente con id " + id +
                    " no existe");
            throw new BadRequestException("El paciente con id " + id +
                    " no existe");
        }
    }
}
