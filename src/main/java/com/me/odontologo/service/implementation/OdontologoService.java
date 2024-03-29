package com.me.odontologo.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.odontologo.dto.request.OdontologoRequestDTO;
import com.me.odontologo.dto.response.OdontologoResponseDTO;
import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.exception.BadRequestException;
import com.me.odontologo.exception.NoContentException;
import com.me.odontologo.repository.IOdontologoRepository;
import com.me.odontologo.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    private ObjectMapper mapper;
    private final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository,
                             ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public OdontologoResponseDTO guardarOdontologo(OdontologoRequestDTO odontologoRequest){
        if(odontologoRequest.getMatricula() != null){
            Odontologo odontologo = mapper
                    .convertValue(odontologoRequest, Odontologo.class);
            odontologoRepository.save(odontologo);
            OdontologoResponseDTO odontologoResponse = mapper
                    .convertValue(odontologo, OdontologoResponseDTO.class);
            return odontologoResponse;
        }else{
            LOGGER.error("Falta el valor del campo matrícula");
            throw new BadRequestException("Falta el valor del campo matrícula");
        }
    }

    @Override
    public OdontologoResponseDTO actualizarOdontologo(Odontologo odontologo){
        if( odontologo.getId() != null
                && odontologoRepository.findById(odontologo.getId()).isPresent()){
            odontologoRepository.save(odontologo);
            OdontologoResponseDTO odontologoResponse = mapper
                    .convertValue(odontologo, OdontologoResponseDTO.class);
            return odontologoResponse;
        }else{
            LOGGER.error("El odontologo buscado para actualizar" +
                    "con id" + odontologo.getId() + " no existe");
            throw new BadRequestException("El odontologo buscado para actualizar" +
                    "con id" + odontologo.getId() + " no existe");
        }
    }

    @Override
    public void eliminarOdontologo(Long id){
        if(odontologoRepository.findById(id).isPresent()){
            odontologoRepository.deleteById(id);
        }else{
            LOGGER.error("El odontologo con id " + id +
                    " no se puedo eliminar porque no existe");
            throw new BadRequestException("El odontologo con id " + id +
                    " no se puedo eliminar porque no existe");
        }
    }
    @Override
    public List<OdontologoResponseDTO> buscarTodosLosOdontologos(){
        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoResponseDTO> odontologosResponse = new ArrayList<>();
        if (!odontologos.isEmpty()){
            for(Odontologo od: odontologos){
                odontologosResponse.add(
                        mapper.convertValue(od, OdontologoResponseDTO.class));
            }
            return odontologosResponse;
        }else{
            LOGGER.error("la petición se realizó con éxito pero" +
                    " la respuesta no tiene contenido");
            throw new NoContentException();
        }
    }
    @Override
    public OdontologoResponseDTO buscar(Long id){
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            return mapper.convertValue(odontologo, OdontologoResponseDTO.class);
        }else{
            LOGGER.error("El odontologo buscado" +
                    "con id " + id + " no existe");
            throw new BadRequestException("El odontologo buscado" +
                    "con id " + id + " no existe");
        }
    }
}
