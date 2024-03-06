package com.me.odontologo.dao;

import com.me.odontologo.domain.Turno;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAOH2 implements IDao<Turno>{

    private static final Logger LOGGER = Logger.getLogger(TurnoDAOH2.class);
    private static final String DB_CONFIG_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_CONFIG_URL = "jdbc:h2:~/mydaodb";
    private static final String DB_CONFIG_USER = "sa";
    private static final String DB_CONFIG_PASSWORD = "";
    private static final String STMT_BUSCAR_TODOS = "SELECT * FROM TURNOS;";
    private static final String PSTMT_BUSCAR = "SELECT * FROM TURNOS WHERE ID = ?;";
    private static final String PSTMT_ELIMINAR = "DELETE FROM TURNOS WHERE ID = ?";
    private static final String PSTMT_GUARDAR = "INSERT INTO TURNOS (ID, FECHA, HORA)" +
            " VALUES (?,?,?)";
    @Override
    public List<Turno> buscarTodos() {
        Connection c = null;
        Statement stmt = null;
        List<Turno> turnos = new ArrayList<>();
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(STMT_BUSCAR_TODOS);
            while(rs.next()){
                Turno turno = new Turno(
                        rs.getInt(1),
                        rs.getDate(2).toLocalDate(),
                        rs.getTime(3).toLocalTime());
                turnos.add(turno);
            }
            rs.close();
        }catch(Exception e){
            LOGGER.error("Error al intentar buscar todos los turnos: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch(Exception ex){
                LOGGER.error("Error al intentar cerrar la conexión al" +
                        "buscar todos los turnos: ", ex);
            }
        }
        return turnos;
    }
    @Override
    public Turno buscar(int id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        Turno turno = new Turno();
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_BUSCAR);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                turno.setId(rs.getInt(1));
                turno.setFecha(rs.getDate(2).toLocalDate());
                turno.setHora(rs.getTime(3).toLocalTime());
            }
            rs.close();
        }catch (Exception e){
            LOGGER.error("Error al buscar un turno por su id: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex){
                LOGGER.error("Error al cerrar la conexion luego" +
                        "de ejecutar la busqueda de un turno por id: ", ex);
            }
        }
        return turno;
    }
    @Override
    public void guardar(Turno turno) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_GUARDAR);
            pstmt.setInt(1, turno.getId());
            pstmt.setDate(2, Date.valueOf(turno.getFecha()));
            pstmt.setTime(3, Time.valueOf(turno.getHora()));
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception e){
            LOGGER.error("Error al guardar turno: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex){
                LOGGER.error("Error al intentar cerrar la conexión luego de ejecutar el guardado" +
                        "de un turno: ", ex);
            }
        }
    }
    @Override
    public void eliminar(int id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_ELIMINAR);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            LOGGER.error("Error al intentar eliminar un turno de la base de datos: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex) {
                LOGGER.error("Error al intentar cerrar la conexión con la base de datos" +
                        "luego de ejecutar la funcion de  eliminar un turno de la misma: ", ex);
            }
        }
    }
}
