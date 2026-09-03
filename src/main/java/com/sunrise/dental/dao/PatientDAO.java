package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.config.DBConnection;
import com.sunrise.dental.model.Patient;

public class PatientDAO {

    private Connection connection;

    public PatientDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    public boolean save(Patient patient) {

        String sql = "INSERT INTO patients (name, address, contact_number) "
                   + "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(
                sql,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, patient.getName());
            statement.setString(2, patient.getAddress());
            statement.setString(3, patient.getContactNumber());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                try (ResultSet resultSet = statement.getGeneratedKeys()) {

                    if (resultSet.next()) {
                        patient.setPatientId(
                                resultSet.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Patient findById(int patientId) {

        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setName(resultSet.getString("name"));
                patient.setAddress(resultSet.getString("address"));
                patient.setContactNumber(
                        resultSet.getString("contact_number")
                );

                return patient;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Patient> findAll() {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setName(resultSet.getString("name"));
                patient.setAddress(resultSet.getString("address"));
                patient.setContactNumber(
                        resultSet.getString("contact_number")
                );

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}