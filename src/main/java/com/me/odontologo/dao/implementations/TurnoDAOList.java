package com.me.odontologo.dao.implementations;

import com.me.odontologo.dao.IDao;
import com.me.odontologo.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDAOList implements IDao<Turno> {
    private List<Turno> turnoList;

    @Autowired
    public TurnoDAOList() {
        this.turnoList = new ArrayList<>();
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnoList;
    }
    @Override
    public Turno buscar(int id) {
        Turno turnoADevolver = null;
        for (Turno t: turnoList) {
            if (t.getId() == id) {
                turnoADevolver = t;
                return turnoADevolver;
            }
        }
        return turnoADevolver;
    }
    @Override
    public Turno guardar(Turno turno) {
        turnoList.add(turno);
        return turno;
    }
    @Override
    public void eliminar(int id) {
        Turno turnoAEliminar = buscar(id);
        turnoList.remove(turnoAEliminar);
    }
}
