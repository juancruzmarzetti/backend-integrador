import dao.OdontologoDAOH2;
import entity.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDAOH2Test {
    @Test
    public void guardarYBuscarTodos() {
        String DB_CREATE_TABLE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
                "CREATE TABLE ODONTOLOGOS ( " +
                "MATRICULA INT PRIMARY KEY, " +
                "NOMBRE VARCHAR(255) NOT NULL," +
                "APELLIDO VARCHAR(255) NOT NULL);";

        try {
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:~/mydaodb", "sa", "");
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DB_CREATE_TABLE_ODONTOLOGOS);

            Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
            Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

            OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
            odontologoService.guardarOdontologo(odontologo1);
            odontologoService.guardarOdontologo(odontologo2);

            List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
            Assertions.assertEquals(2, odontologos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarUno() {
        String DB_CREATE_TABLE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
                "CREATE TABLE ODONTOLOGOS ( " +
                "MATRICULA INT PRIMARY KEY, " +
                "NOMBRE VARCHAR(255) NOT NULL, " +
                "APELLIDO VARCHAR(255) NOT NULL);";

        try {
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:~/mydaodb", "sa", "");
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DB_CREATE_TABLE_ODONTOLOGOS);

            Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
            Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

            OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
            odontologoService.guardarOdontologo(odontologo1);
            odontologoService.guardarOdontologo(odontologo2);
            odontologoService.eliminarOdontologo(odontologo1.getMatricula());

            List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
            Assertions.assertEquals(1, odontologos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void buscarUno() {
        String DB_CREATE_TABLE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
                "CREATE TABLE ODONTOLOGOS ( " +
                "MATRICULA INT PRIMARY KEY, " +
                "NOMBRE VARCHAR(255) NOT NULL, " +
                "APELLIDO VARCHAR(255) NOT NULL);";

        try {
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:~/mydaodb", "sa", "");
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DB_CREATE_TABLE_ODONTOLOGOS);

            Odontologo odontologo1 = new Odontologo(1, "Jorge", "Lopez");
            Odontologo odontologo2 = new Odontologo(2, "Lionel", "Messi");

            OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
            odontologoService.guardarOdontologo(odontologo1);
            odontologoService.guardarOdontologo(odontologo2);
            odontologoService.buscar(odontologo1.getMatricula());
            Assertions.assertEquals(1, odontologoService.buscar(odontologo1.getMatricula()).getMatricula());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}