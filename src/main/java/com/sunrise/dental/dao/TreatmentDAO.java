package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.config.DBConnection;
import com.sunrise.dental.model.Treatment;

public class TreatmentDAO {

    private Connection connection;

    public TreatmentDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    public Treatment findById(int treatmentId) {

        String sql = "SELECT * FROM treatments WHERE treatment_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatmentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Treatment treatment = new Treatment();

                treatment.setTreatmentId(
                        resultSet.getInt("treatment_id")
                );

                treatment.setType(
                        resultSet.getString("type")
                );

                treatment.setFee(
                        resultSet.getDouble("fee")
                );

                return treatment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Treatment findByType(String type) {

        String sql = "SELECT * FROM treatments WHERE type = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, type);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Treatment treatment = new Treatment();

                treatment.setTreatmentId(
                        resultSet.getInt("treatment_id")
                );

                treatment.setType(
                        resultSet.getString("type")
                );

                treatment.setFee(
                        resultSet.getDouble("fee")
                );

                return treatment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Treatment> findAll() {

        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT * FROM treatments";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Treatment treatment = new Treatment();

                treatment.setTreatmentId(
                        resultSet.getInt("treatment_id")
                );

                treatment.setType(
                        resultSet.getString("type")
                );

                treatment.setFee(
                        resultSet.getDouble("fee")
                );

                treatments.add(treatment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treatments;
    }
}