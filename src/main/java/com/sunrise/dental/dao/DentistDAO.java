package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.config.DBConnection;
import com.sunrise.dental.model.Dentist;

public class DentistDAO {

    private Connection connection;

    public DentistDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    public Dentist findById(int dentistId) {

        String sql = "SELECT * FROM dentists WHERE dentist_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dentistId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Dentist dentist = new Dentist();

                dentist.setDentistId(resultSet.getInt("dentist_id"));
                dentist.setName(resultSet.getString("name"));

                return dentist;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Dentist> findAll() {

        List<Dentist> dentists = new ArrayList<>();

        String sql = "SELECT * FROM dentists";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Dentist dentist = new Dentist();

                dentist.setDentistId(
                        resultSet.getInt("dentist_id")
                );

                dentist.setName(
                        resultSet.getString("name")
                );

                dentists.add(dentist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dentists;
    }
}