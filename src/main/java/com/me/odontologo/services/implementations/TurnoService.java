package com.me.odontologo.services.implementations;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.dao.implementations.TurnoDAOList;
import com.me.odontologo.model.Turno;
import com.me.odontologo.services.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private IDao<Turno> turnoDAO;

    @Autowired
    public TurnoService(TurnoDAOList turnoDAO) {
        this.turnoDAO = turnoDAO;
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
