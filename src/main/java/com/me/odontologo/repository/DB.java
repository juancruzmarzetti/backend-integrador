package com.me.odontologo.repository;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
    private static final Logger LOGGER = Logger.getLogger(DB.class);
    private static final String SQL_DROP_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS " +
            "DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " CALLE VARCHAR(100) NOT NULL," +
            " NUMERO INT NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL," +
            " PROVINCIA VARCHAR(100) NOT NULL" +
            ")";
    private static final String SQL_DROP_CREATE_LOGS = "DROP TABLE IF EXISTS LOGS;"
        + " CREATE TABLE LOGS("
            + " USER_ID VARCHAR(20) NOT NULL,"
            + " DATED DATETIME NOT NULL,"
            + " LOGGER VARCHAR(50) NOT NULL,"
            + " LEVEL VARCHAR(10) NOT NULL,"
            + " MESSAGE VARCHAR(1000) NOT NULL);";
    private static final String SQL_DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS PACIENTES;"
        + " CREATE TABLE PACIENTES("
            + " ID INT AUTO_INCREMENT PRIMARY KEY,"
            + " NOMBRE VARCHAR(50) NOT NULL,"
            + " APELLIDO VARCHAR(50) NOT NULL,"
            + " DOMICILIO_ID INT NOT NULL,"
            + " DNI INT NOT NULL,"
            + " FECHA_DE_ALTA DATE NOT NULL,"
            + " USUARIO VARCHAR(50) NOT NULL,"
            + " PASSWORD VARCHAR(50) NOT NULL);";
    private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;"
        + " CREATE TABLE ODONTOLOGOS("
            + " ID INT AUTO_INCREMENT PRIMARY KEY,"
            + " MATRICULA INT NOT NULL,"
            + " NOMBRE VARCHAR(255) NOT NULL,"
            + " APELLIDO VARCHAR(255) NOT NULL);";
    //private static final String SQL_INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) " +
            // "VALUES ('Diego', 'Gimenez', '123')";
    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/mydaodb",
                "sa", "");
    }
    public static void crearTablas() {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_DROP_CREATE_LOGS);
            statement.execute(SQL_DROP_CREATE_PACIENTES);
            statement.execute(SQL_DROP_CREATE_DOMICILIOS);
            statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);
            LOGGER.info("Se crearon las tablas odontologos, domicilios y pacientes");
            // statement.execute(SQL_INSERT_ODONTOLOGOS);
            // LOGGER.info("se insertó un valor en la tabla odontologos");
        } catch (Exception e) {
            LOGGER.error("Error en la creacion de las tablas SQL: ", e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al intentar cerrar la conexión luego" +
                        "de la creación de las tablas SQL", e);
            }
        }
    }
}