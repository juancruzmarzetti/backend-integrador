package com.me.odontologo.service.implementation;

import com.me.odontologo.entity.Odontologo;
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
        return odontologoRepository.save(odontologo);
    }
    @Override
    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }
    @Override
    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoRepository.findAll();
    }
    @Override
    public Optional<Odontologo> buscar(Long id){
        return odontologoRepository.findById(id);
    }
}
