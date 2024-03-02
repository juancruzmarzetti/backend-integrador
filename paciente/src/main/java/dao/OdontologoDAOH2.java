package dao;
import entity.Odontologo;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo>{
    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);
    private static final String DB_CONFIG_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_CONFIG_URL = "jdbc:h2:~/mydaodb";
    private static final String DB_CONFIG_USER = "sa";
    private static final String DB_CONFIG_PASSWORD = "";
    private static final String STMT_BUSCAR_TODOS = "SELECT * FROM ODONTOLOGOS;";
    private static final String PSTMT_BUSCAR = "SELECT * FROM ODONTOLOGOS WHERE MATRICULA = ?;";
    private static final String PSTMT_ELIMINAR = "DELETE FROM ODONTOLOGOS WHERE MATRICULA = ?";
    private static final String PSTMT_GUARDAR = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO)" +
            "VALUES (?,?,?)";

    @Override
    public List<Odontologo> buscarTodos() {
        Connection c = null;
        Statement stmt = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(STMT_BUSCAR_TODOS);
            while(rs.next()){
                Odontologo odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                odontologos.add(odontologo);
            }
        }catch(Exception e){
            LOGGER.error("Error al intentar buscar a todos los odontologos: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch(Exception ex){
                LOGGER.error("Error al intentar cerrar la conexión al" +
                        "buscar a todos los odontologos: ", ex);
            }
        }
        return odontologos;
    }

    @Override
    public Odontologo buscar(int id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        Odontologo odontologo = new Odontologo();
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_BUSCAR);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                odontologo.setMatricula(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));
            }
            rs.close();
        }catch(Exception e){
            LOGGER.error("Error al buscar un odontologo por su matricula: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex){
                LOGGER.error("Error al cerrar la conexion luego" +
                        "de ejecutar la busqueda por matricula: ", ex);
            }
        }
        return odontologo;
    }

    @Override
    public void guardar(Odontologo odontologo) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_GUARDAR);
            pstmt.setInt(1, odontologo.getMatricula());
            pstmt.setString(2, odontologo.getNombre());
            pstmt.setString(3, odontologo.getApellido());
            pstmt.executeUpdate();
        }catch (Exception e){
            LOGGER.error("Error al guardar odontologo: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception ex){
                LOGGER.error("Error al intentar cerrar la conexión luego de ejecutar el guardado" +
                        "de un odontologo: ", ex);
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
        }catch (Exception e){
            LOGGER.error("Error al intentar eliminar un odontologo de la base de datos: ", e);
        }finally{
            try{
                assert c != null;
                c.close();
            }catch (Exception exc) {
                LOGGER.error("Error al intentar cerrar la conexión con la base de datos" +
                        "luego de ejecutar la funcion de  eliminar un odontologo de la misma: ", exc);
            }
        }
    }
}