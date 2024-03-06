package com.me.odontologo.dao;

import com.me.odontologo.domain.Domicilio;
import org.apache.log4j.Logger;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio>{
    private static final Logger LOGGER = Logger.getLogger(DomicilioDAOH2.class);

    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";


    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }

    @Override
    public Domicilio buscar(int id) {
        return null;
    }

    @Override
    public void guardar(Domicilio domicilio) {

    }

    @Override
    public void eliminar(int id) {

    }
}
