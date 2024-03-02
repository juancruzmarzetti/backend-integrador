import dao.OdontologoDAOArrayListImpl;
import entity.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDAOArrayListImplTest {

    @Test
    void buscarTodosYAgregar() {
        Odontologo odontologo1 = new Odontologo(1, "Juan", "Perez");
        Odontologo odontologo2 = new Odontologo(2, "Juan2", "Perez2");
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOArrayListImpl());
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        Assertions.assertEquals(2, odontologos.size());
    }

    @Test
    void buscarYEliminar() {
        Odontologo odontologo1 = new Odontologo(1, "Juan", "Perez");
        Odontologo odontologo2 = new Odontologo(2, "Juan2", "Perez2");
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOArrayListImpl());
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.eliminarOdontologo(odontologo1.getMatricula());
        Odontologo odontologo2Buscado = odontologoService.buscar(odontologo2.getMatricula());
        Assertions.assertEquals(odontologo2.getMatricula(), odontologo2Buscado.getMatricula());
    }
}