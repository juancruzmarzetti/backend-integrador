package com.me.odontologo.services;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Odontologo;
import com.me.odontologo.domain.Turno;

import java.util.List;

public class TurnoService {
    private IDao<Turno> turnoDAO;
    public TurnoService(){};
    public TurnoService(IDao<Odontologo> odontologoDAO) {
        this.turnoDAO = turnoDAO;
    }
    public void guardarTurno(Turno turno){
        turnoDAO.guardar(turno);
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
