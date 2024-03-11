package com.me.odontologo.services.implementations;
import com.me.odontologo.repository.IRepository;
import com.me.odontologo.repository.implementations.OdontologoRepositoryH2;
import com.me.odontologo.model.Odontologo;
import com.me.odontologo.services.IOdontologoService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {
    private IRepository<Odontologo> odontologoDAO;
    public OdontologoService() {
        odontologoDAO = new OdontologoRepositoryH2();
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
