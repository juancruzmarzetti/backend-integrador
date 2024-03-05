package com.me.odontologo.dao;

import com.me.odontologo.domain.Odontologo;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOArrayListImpl implements IDao<Odontologo> {
    private List<Odontologo> odontologos;
    public OdontologoDAOArrayListImpl() {
        odontologos = new ArrayList<>();
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologos;
    }

    @Override
    public Odontologo buscar(int id) {
        Odontologo odontologo = null;
        int i;
        for(i = 0; i < odontologos.size(); i++){
            if(i == odontologos.get(i).getMatricula()){
                odontologo = odontologos.get(i);
            }
        }
        return odontologo;
    }

    @Override
    public void guardar(Odontologo odontologo) {
        int siguienteMatricula = odontologos.size() + 1;
        odontologo.setMatricula(siguienteMatricula);
        odontologos.add(odontologo);
    }

    @Override
    public void eliminar(int id) {
        int i;
        for(i = 0; i < odontologos.size(); i++){
            if(i == odontologos.get(i).getMatricula()){
                odontologos.remove(i);
            }
        }
    }
}
