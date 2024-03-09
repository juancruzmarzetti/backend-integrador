package com.me.odontologo.services;
import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Odontologo;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OdontologoService {
    //TODO Inicializar Dao en constructor
    private IDao<Odontologo> odontologoDAO;
    public OdontologoService(){};
    public OdontologoService(IDao<Odontologo> odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoDAO.guardar(odontologo);
    }
    public void eliminarOdontologo(int id){
        odontologoDAO.eliminar(id);
    }
    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoDAO.buscarTodos();
    }
    public Odontologo buscar(int id){
        return odontologoDAO.buscar(id);
    }
}
