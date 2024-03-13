package com.me.odontologo.services.implementations;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.dao.implementations.PacienteDAOH2;
import com.me.odontologo.model.Paciente;
import com.me.odontologo.services.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteDAO;
    @Autowired
    public PacienteService(PacienteDAOH2 pacienteDAOH2) {
        this.pacienteDAO = pacienteDAOH2;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteDAO.guardar(paciente);
    }
    @Override
    public void eliminarPaciente(int id){
        pacienteDAO.eliminar(id);
    }
    @Override
    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteDAO.buscarTodos();
    }
    @Override
    public Paciente buscar(int id){
        return pacienteDAO.buscar(id);
    }
}
