package com.me.odontologo.dao;

import com.me.odontologo.domain.Domicilio;
import com.me.odontologo.services.DomicilioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

class DomicilioDAOH2Test {
    @Test
    public void guardarYBuscarTodos() {
        String DB_CREATE_TABLE_DOMICILIOS = "DROP TABLE IF EXISTS DOMICILIOS;" +
                "CREATE TABLE DOMICILIOS ( " +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "CALLE VARCHAR(255) NOT NULL, " +
                "NUMERO INT NOT NULL, " +
                "LOCALIDAD VARCHAR(255) NOT NULL, " +
                "PROVINCIA VARCHAR(255) NOT NULL);";

        try {
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:~/mydaodb", "sa", "");
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DB_CREATE_TABLE_DOMICILIOS);

            Domicilio domicilio1 = new Domicilio(1, "Av. Rivadavia", 1234, "Buenos Aires", "CABA");
            Domicilio domicilio2 = new Domicilio(2, "Av. de Mayo", 5678, "La Plata", "Buenos Aires");

            DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
            domicilioService.guardarDomicilio(domicilio1);
            domicilioService.guardarDomicilio(domicilio2);

            List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();
            Assertions.assertEquals(2, domicilios.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarUno() {
        String DB_CREATE_TABLE_DOMICILIOS = "DROP TABLE IF EXISTS DOMICILIOS;" +
                "CREATE TABLE DOMICILIOS ( " +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "CALLE VARCHAR(255) NOT NULL, " +
                "NUMERO INT NOT NULL, " +
                "LOCALIDAD VARCHAR(255) NOT NULL, " +
                "PROVINCIA VARCHAR(255) NOT NULL);";

        try {
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:~/mydaodb", "sa", "");
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DB_CREATE_TABLE_DOMICILIOS);

            Domicilio domicilio1 = new Domicilio(1, "Av. Rivadavia", 1234, "Buenos Aires", "CABA");
            Domicilio domicilio2 = new Domicilio(2, "Av. de Mayo", 5678, "La Plata", "Buenos Aires");

            DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
            domicilioService.guardarDomicilio(domicilio1);
            domicilioService.guardarDomicilio(domicilio2);
            domicilioService.eliminarDomicilio(domicilio1.getId());

            List<Domicilio> domicilios = domicilioService.buscarTodosLosDomicilios();
            Assertions.assertEquals(1, domicilios.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buscarUno() {
        String DB_CREATE_TABLE_DOMICILIOS = "DROP TABLE IF EXISTS DOMICILIOS;" +
                "CREATE TABLE DOMICILIOS ( " +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "CALLE VARCHAR(255) NOT NULL, " +
                "NUMERO INT NOT NULL, " +
                "LOCALIDAD VARCHAR(255) NOT NULL, " +
                "PROVINCIA VARCHAR(255) NOT NULL);";

        try {
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:~/mydaodb", "sa", "");
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DB_CREATE_TABLE_DOMICILIOS);

            Domicilio domicilio1 = new Domicilio(1, "Av. Rivadavia", 1234, "Buenos Aires", "CABA");
            Domicilio domicilio2 = new Domicilio(2, "Av. de Mayo", 5678, "La Plata", "Buenos Aires");

            DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
            domicilioService.guardarDomicilio(domicilio1);
            domicilioService.guardarDomicilio(domicilio2);
            domicilioService.buscarDomicilio(domicilio1.getId());
            Assertions.assertEquals(1, domicilioService.buscarDomicilio(domicilio1.getId()).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
