package com.me.odontologo.services.implementations;

import com.me.odontologo.repository.IRepository;
import com.me.odontologo.repository.implementations.TurnoRepositoryList;
import com.me.odontologo.model.Turno;
import com.me.odontologo.services.ITurnoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private IRepository<Turno> turnoDAO;

    public TurnoService() {
        turnoDAO = new TurnoRepositoryList();
    }

    @Override
    public Turno guardarTurno(Turno turno){
        return turnoDAO.guardar(turno);
    }
    @Override
    public void eliminarTurno(int id){
        turnoDAO.eliminar(id);
    }
    @Override
    public List<Turno> buscarTodosLosTurnos(){
        return turnoDAO.buscarTodos();
    }
    @Override
    public Turno buscar(int id){
        return turnoDAO.buscar(id);
    }
}
