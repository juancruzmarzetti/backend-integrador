package com.me.odontologo.services;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Paciente;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {
    //TODO Inicializar Dao en constructor
    private IDao<Paciente> pacienteDAO;
    public PacienteService(){};
    public PacienteService(IDao<Paciente> pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteDAO.guardar(paciente);
    }
    public void eliminarPaciente(int id){
        pacienteDAO.eliminar(id);
    }
    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteDAO.buscarTodos();
    }
    public Paciente buscar(int id){
        return pacienteDAO.buscar(id);
    }
}
