package com.me.odontologo.services.implementations;

import com.me.odontologo.repository.IRepository;
import com.me.odontologo.repository.implementations.DomicilioRepositoryH2;
import com.me.odontologo.model.Domicilio;
import com.me.odontologo.services.IDomicilioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements IDomicilioService {
    private IRepository<Domicilio> domicilioDAO;
    public DomicilioService() {
        domicilioDAO = new DomicilioRepositoryH2();
    }

    @Override
    public Domicilio guardarDomicilio(Domicilio domicilio){
            return domicilioDAO.guardar(domicilio);
    }
    @Override
    public List<Domicilio> buscarTodosLosDomicilios(){
        return domicilioDAO.buscarTodos();
    }
    @Override
    public void eliminarDomicilio(int id){
        domicilioDAO.eliminar(id);
    }
    @Override
    public Domicilio buscarDomicilio(int id){
        return domicilioDAO.buscar(id);
    }
}
