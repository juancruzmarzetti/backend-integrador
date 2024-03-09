package com.me.odontologo.services;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Odontologo;
import com.me.odontologo.domain.Turno;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService {
    private IDao<Turno> turnoDAO;
    //TODO implementar TurnoDAOList en vez de TurnoDAOH2. inicializar Dao en constructor
    public TurnoService(IDao<Turno> turnoDAO) {
        this.turnoDAO = turnoDAO;
    }
    public Turno guardarTurno(Turno turno){
        return turnoDAO.guardar(turno);
    }
    public void eliminarTurno(int id){
        turnoDAO.eliminar(id);
    }
    public List<Turno> buscarTodosLosTurnos(){
        return turnoDAO.buscarTodos();
    }
    public Turno buscar(int id){
        return turnoDAO.buscar(id);
    }
}
