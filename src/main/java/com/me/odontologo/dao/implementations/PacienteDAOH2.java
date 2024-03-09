package com.me.odontologo.dao.implementations;

import com.me.odontologo.dao.DB;
import com.me.odontologo.dao.IDao;
import com.me.odontologo.domain.Domicilio;
import com.me.odontologo.domain.Paciente;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements IDao<Paciente> {
    private static final Logger LOGGER = Logger.getLogger(PacienteDAOH2.class);
    private static final String DB_CONFIG_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_CONFIG_URL = "jdbc:h2:~/mydaodb";
    private static final String DB_CONFIG_USER = "sa";
    private static final String DB_CONFIG_PASSWORD = "";
    private static final String SQL_INSERT =
            "INSERT INTO PACIENTES (ID, NOMBRE, APELLIDO, DOMICILIO_ID, " +
                    "DNI, FECHA_DE_ALTA, USUARIO, PASSWORD) " +
                    "VALUES (?,?,?,?,?,?,?,?);";
    private static final String PSTMT_BUSCAR = "SELECT * FROM PACIENTES WHERE ID = ?;";
    private static final String PSTMT_ELIMINAR = "DELETE FROM PACIENTES WHERE ID = ?";
    private static final String PSTMT_GUARDAR = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO_ID, DNI, FECHA_DE_ALTA, USUARIO, PASSWORD)" +
            "VALUES (?,?,?,?,?,?,?)";
    //private static final String SQL_UPDATE = "UPDATE PACIENTES SET PASSWORD=? WHERE ID=?;";
    private static final String SQL_SELECT_PACIENTES = "SELECT * FROM PACIENTES";
    /*
    public static void main(String[] args) {
        Paciente paciente = new Paciente(
                1,
                "Carlos",
                "Espinoza",
                "Palermo 123, Buenos Aires",
                45345678,
                LocalDate.of(2020, 7, 20),
                "Carlos123",
                "carlitos24");

        Connection c = null;
        try{
            c = getConnection();
            Statement stmt = c.createStatement();
            //stmt.execute(SQL_CREATE_LOGS);
            Statement stmt2 = c.createStatement();

            PreparedStatement pstmtInsert = c.prepareStatement(SQL_INSERT);
            pstmtInsert.setInt(1, paciente.getId());
            pstmtInsert.setString(2, paciente.getNombre());
            pstmtInsert.setString(3, paciente.getApellido());
            pstmtInsert.setString(4, paciente.getDomicilio());
            pstmtInsert.setInt(5, paciente.getDni());
            pstmtInsert.setDate(6, Date.valueOf(paciente.getFechaDeAlta()));
            pstmtInsert.setString(7, paciente.getUsuario());
            pstmtInsert.setString(8, paciente.getPassword());
            pstmtInsert.execute();

            c.setAutoCommit(false);
            PreparedStatement pstmtUpdate = c.prepareStatement(SQL_UPDATE);
            pstmtUpdate.setString(1, paciente.setPassword("Contraseña cambiada"));
            pstmtUpdate.setInt(2, paciente.getId());
            pstmtUpdate.execute();
            c.commit();
            c.setAutoCommit(true);

            Statement stmt3 = c.createStatement();
            ResultSet rd = stmt3.executeQuery(SQL_SELECT_PACIENTES);
            while(rd.next()){
                LOGGER.info("¡Se ha llamado a la base de datos con éxito!");
                System.out.println(
                        "ID: " + rd.getInt(1) + ", "
                                + "NOMBRE: " + rd.getString(2) + ", "
                                + "APELLIDO: " + rd.getString(3) + ", "
                                + "DOMICILIO: " + rd.getString(4) + ", "
                                + "DNI: " + rd.getInt(5) + ", "
                                + "FECHA DE ALTA: " + rd.getDate(6) + ", "
                                + "USUARIO: " + rd.getString(7) + ", "
                                + "CONTRASEÑA: " + rd.getString(8));
            }
        }catch(Exception e){
            LOGGER.error("Error en la comunicación con la base de datos: ", e);
            try{
                assert c != null;
                c.rollback();
            }catch(SQLException e2){
                LOGGER.error("No se pudo hacer el rollback: ", e2);
            }
        }finally{
            try {
                assert c != null;
                c.close();
            }catch (SQLException e3){
                LOGGER.error("No se pudo cerrar la conexión con la base de datos: ", e3);
            }
        }
    }
    */
    @Override
    public List<Paciente> buscarTodos() {
        Connection c = null;
        Statement stmt = null;
        List<Paciente> pacientes = new ArrayList<>();
        try{
            c = DB.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_PACIENTES);
            while(rs.next()){
                DomicilioDAOH2 domicilioDaoH2 = new DomicilioDAOH2();
                Domicilio domicilio = domicilioDaoH2.buscar(rs.getInt(4));

                Paciente paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        domicilio,
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(),
                        rs.getString(7),
                        rs.getString(8)
                );
                pacientes.add(paciente);
            }
            rs.close();
        }catch(Exception e){
            LOGGER.error("Error al intentar buscar a todos los pacientes: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch(Exception ex){
                LOGGER.error("Error al intentar cerrar la conexión al" +
                        "buscar a todos los pacientes: ", ex);
            }
        }
        return pacientes;
    }
    @Override
    public Paciente buscar(int id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        Paciente paciente = new Paciente();
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_BUSCAR);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            DomicilioDAOH2 domicilioDaoH2 = new DomicilioDAOH2();

            while(rs.next()){
                Domicilio domicilio = domicilioDaoH2.buscar(rs.getInt(4));

                paciente.setId(id);
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDomicilio(domicilio);
                paciente.setDni(rs.getInt(5));
                paciente.setFechaDeAlta(rs.getDate(6).toLocalDate());
                paciente.setUsuario(rs.getString(7));
                paciente.setPassword(rs.getString(8));
            }
            rs.close();
        }catch (Exception e){
            LOGGER.error("Error al buscar un paciente por su id: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex){
                LOGGER.error("Error al cerrar la conexion luego" +
                        "de ejecutar la busqueda por id del paciente: ", ex);
            }
        }
        return paciente;
    }
    @Override
    public Paciente guardar(Paciente paciente) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            c = DB.getConnection();
            pstmt = c.prepareStatement(PSTMT_GUARDAR, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellido());
            pstmt.setInt(3, paciente.getDomicilio().getId());
            pstmt.setInt(4, paciente.getDni());
            pstmt.setDate(5, Date.valueOf(paciente.getFechaDeAlta()));
            pstmt.setString(6, paciente.getUsuario());
            pstmt.setString(7, paciente.getPassword());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                paciente.setId(rs.getInt(1));
            }
            pstmt.close();
        }catch (Exception e){
            LOGGER.error("Error al guardar paciente: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex){
                LOGGER.error("Error al intentar cerrar la conexión luego de ejecutar el guardado" +
                        "de un paciente: ", ex);
            }
        }
        return paciente;
    }
    @Override
    public void eliminar(int id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            c = DB.getConnection();
            pstmt = c.prepareStatement(PSTMT_ELIMINAR);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception e){
            LOGGER.error("Error al intentar eliminar un paciente de la base de datos: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex) {
                LOGGER.error("Error al intentar cerrar la conexión con la base de datos" +
                        "luego de ejecutar la funcion de  eliminar un paciente de la misma: ", ex);
            }
        }
    }
}
