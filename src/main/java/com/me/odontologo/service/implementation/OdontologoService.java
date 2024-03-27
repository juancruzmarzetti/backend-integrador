package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Odontologo;
import com.me.odontologo.exception.BadRequestException;
import com.me.odontologo.exception.NoContentException;
import com.me.odontologo.repository.IOdontologoRepository;
import com.me.odontologo.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo){
        if(odontologo.getMatricula() != null){
            return odontologoRepository.save(odontologo);
        }else{
            throw new BadRequestException("Falta el valor del campo matrícula");
        }
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo){
        if( odontologo.getId() != null
                && odontologoRepository.findById(odontologo.getId()).isPresent()){
            return odontologoRepository.save(odontologo);
        }else{
            throw new BadRequestException("El odontologo buscado para actualizar no existe");
        }
    }

    @Override
    public void eliminarOdontologo(Long id){
        if(odontologoRepository.findById(id).isPresent()){
            odontologoRepository.deleteById(id);
        }else{
            throw new BadRequestException("El odontologo no se puedo eliminar" +
                    " porque no existe");
        }
    }
    @Override
    public List<Odontologo> buscarTodosLosOdontologos(){
        List<Odontologo> odontologos = odontologoRepository.findAll();
        if (!odontologos.isEmpty()){
            return odontologos;
        }else{
            throw new NoContentException();
        }
    }
    @Override
    public Optional<Odontologo> buscar(Long id){
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            return odontologo;
        }else{
            throw new BadRequestException("El odontologo buscado no existe");
        }
    }
}
