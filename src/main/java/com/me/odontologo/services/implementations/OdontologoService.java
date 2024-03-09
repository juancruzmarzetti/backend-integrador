package com.me.odontologo.services.implementations;
import com.me.odontologo.dao.IDao;
import com.me.odontologo.dao.implementations.OdontologoDAOH2;
import com.me.odontologo.domain.Odontologo;
import com.me.odontologo.services.IOdontologoService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoDAO;
    public OdontologoService() {
        odontologoDAO = new OdontologoDAOH2();
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoDAO.guardar(odontologo);
    }
    @Override
    public void eliminarOdontologo(int id){
        odontologoDAO.eliminar(id);
    }
    @Override
    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoDAO.buscarTodos();
    }
    @Override
    public Odontologo buscar(int id){
        return odontologoDAO.buscar(id);
    }
}
