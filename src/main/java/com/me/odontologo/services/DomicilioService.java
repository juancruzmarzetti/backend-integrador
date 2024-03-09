package com.me.odontologo.services;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Domicilio;
import com.me.odontologo.domain.Odontologo;

import java.util.List;

public class DomicilioService {
    //TODO Inicializar Dao en constructor
    private IDao<Domicilio> domicilioDAO;
    public DomicilioService(IDao<Domicilio> domicilioDAO) {
        this.domicilioDAO = domicilioDAO;
    }
    public Domicilio guardarDomicilio(Domicilio domicilio){
            return domicilioDAO.guardar(domicilio);
    };
    public List<Domicilio> buscarTodosLosDomicilios(){
        return domicilioDAO.buscarTodos();
    };
    public void eliminarDomicilio(int id){
        domicilioDAO.eliminar(id);
    }
    public Domicilio buscarDomicilio(int id){
        return domicilioDAO.buscar(id);
    }
}
