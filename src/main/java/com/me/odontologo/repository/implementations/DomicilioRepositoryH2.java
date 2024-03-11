package com.me.odontologo.repository.implementations;

import com.me.odontologo.repository.DB;
import com.me.odontologo.repository.IRepository;
import com.me.odontologo.model.Domicilio;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioRepositoryH2 implements IRepository<Domicilio> {
    private static final Logger LOGGER = Logger.getLogger(DomicilioRepositoryH2.class);
    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";
    private static final String SELECT_BY_ID = "SELECT * FROM DOMICILIOS WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM DOMICILIOS WHERE ID = ?";

    @Override
    public List<Domicilio> buscarTodos() {
        List<Domicilio> domicilios = new ArrayList<>();
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Domicilio domicilio = new Domicilio(
                        resultSet.getInt("ID"),
                        resultSet.getString("CALLE"),
                        resultSet.getInt("NUMERO"),
                        resultSet.getString("LOCALIDAD"),
                        resultSet.getString("PROVINCIA"));
                domicilios.add(domicilio);
            }
        } catch (Exception e) {
            LOGGER.error("Error al buscar todos los domicilios: ", e);
        }
        return domicilios;
    }

    @Override
    public Domicilio buscar(int id) {
        Domicilio domicilio = null;
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    domicilio = new Domicilio(
                            resultSet.getInt("ID"),
                            resultSet.getString("CALLE"),
                            resultSet.getInt("NUMERO"),
                            resultSet.getString("LOCALIDAD"),
                            resultSet.getString("PROVINCIA"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al buscar domicilio por ID: ", e);
        }
        return domicilio;
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS)
            ){
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                domicilio.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            LOGGER.error("Error al guardar domicilio: ", e);
        }
        return domicilio;
    }

    @Override
    public void eliminar(int id) {
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error al eliminar domicilio: ", e);
        }
    }
}
