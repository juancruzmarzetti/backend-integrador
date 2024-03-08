package com.me.odontologo.dao;

import com.me.odontologo.domain.Domicilio;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio> {
    private static final Logger LOGGER = Logger.getLogger(DomicilioDAOH2.class);
    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";
    private static final String SELECT_BY_ID = "SELECT * FROM DOMICILIOS WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM DOMICILIOS WHERE ID = ?";

    @Override
    public List<Domicilio> buscarTodos() {
        List<Domicilio> domicilios = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
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
        } catch (SQLException e) {
            LOGGER.error("Error al buscar todos los domicilios: ", e);
        }
        return domicilios;
    }

    @Override
    public Domicilio buscar(int id) {
        Domicilio domicilio = null;
        try (Connection connection = ConnectionFactory.getConnection();
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
        } catch (SQLException e) {
            LOGGER.error("Error al buscar domicilio por ID: ", e);
        }
        return domicilio;
    }

    @Override
    public void guardar(Domicilio domicilio) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOMICILIO)) {
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al guardar domicilio: ", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar domicilio: ", e);
        }
    }
}
