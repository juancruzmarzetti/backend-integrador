package com.me.odontologo.services.implementations;

import com.me.odontologo.repository.IRepository;
import com.me.odontologo.repository.implementations.PacienteRepositoryH2;
import com.me.odontologo.model.Paciente;
import com.me.odontologo.services.IPacienteService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IRepository<Paciente> pacienteDAO;
    public PacienteService() {
        pacienteDAO = new PacienteRepositoryH2();
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
