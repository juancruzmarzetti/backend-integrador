package com.me.odontologo.services;
import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Odontologo;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoDAO;
    public OdontologoService(){};
    public OdontologoService(IDao<Odontologo> odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }
    public void guardarOdontologo(Odontologo odontologo){
        odontologoDAO.guardar(odontologo);
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
