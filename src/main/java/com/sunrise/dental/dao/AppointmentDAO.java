package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.config.DBConnection;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;

public class AppointmentDAO {

    private Connection connection;

    public AppointmentDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    public boolean save(Appointment appointment) {

        String sql = "INSERT INTO appointments "
                + "(patient_id, dentist_id, treatment_id, appointment_date, appointment_time, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(
                sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, appointment.getPatient().getPatientId());
            statement.setInt(2, appointment.getDentist().getDentistId());
            statement.setInt(3, appointment.getTreatment().getTreatmentId());
            statement.setDate(4, java.sql.Date.valueOf(appointment.getAppointmentDate()));
            statement.setTime(5, java.sql.Time.valueOf(appointment.getAppointmentTime()));
            statement.setString(6, appointment.getStatus());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                try (ResultSet resultSet = statement.getGeneratedKeys()) {

                    if (resultSet.next()) {
                        appointment.setAppointmentNo(resultSet.getInt(1));
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Appointment findByAppointmentNo(int appointmentNo) {

        String sql = "SELECT "
                   + "a.appointment_no, "
                   + "a.appointment_date, "
                   + "a.appointment_time, "
                   + "a.status, "
                   + "p.patient_id, p.name AS patient_name, "
                   + "p.address, p.contact_number, "
                   + "d.dentist_id, d.name AS dentist_name, "
                   + "t.treatment_id, t.type, t.fee "
                   + "FROM appointments a "
                   + "JOIN patients p ON a.patient_id = p.patient_id "
                   + "JOIN dentists d ON a.dentist_id = d.dentist_id "
                   + "JOIN treatments t ON a.treatment_id = t.treatment_id "
                   + "WHERE a.appointment_no = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentNo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(
                        resultSet.getInt("patient_id")
                );

                patient.setName(
                        resultSet.getString("patient_name")
                );

                patient.setAddress(
                        resultSet.getString("address")
                );

                patient.setContactNumber(
                        resultSet.getString("contact_number")
                );

                Dentist dentist = new Dentist();

                dentist.setDentistId(
                        resultSet.getInt("dentist_id")
                );

                dentist.setName(
                        resultSet.getString("dentist_name")
                );

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

                Appointment appointment = new Appointment();

                appointment.setAppointmentNo(
                        resultSet.getInt("appointment_no")
                );

                appointment.setAppointmentDate(
                        resultSet.getDate("appointment_date").toLocalDate()
                );

                appointment.setAppointmentTime(
                        resultSet.getTime("appointment_time").toLocalTime()
                );

                appointment.setStatus(
                        resultSet.getString("status")
                );

                appointment.setPatient(patient);
                appointment.setDentist(dentist);
                appointment.setTreatment(treatment);

                return appointment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isDentistAvailable(
            int dentistId,
            java.time.LocalDate date,
            java.time.LocalTime time) {

        String sql = "SELECT appointment_no "
                   + "FROM appointments "
                   + "WHERE dentist_id = ? "
                   + "AND appointment_date = ? "
                   + "AND appointment_time = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dentistId);
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));

            ResultSet resultSet = statement.executeQuery();

            return !resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Appointment> findAll() {

        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT "
                   + "a.appointment_no, "
                   + "a.appointment_date, "
                   + "a.appointment_time, "
                   + "a.status, "
                   + "p.patient_id, p.name AS patient_name, "
                   + "p.address, p.contact_number, "
                   + "d.dentist_id, d.name AS dentist_name, "
                   + "t.treatment_id, t.type, t.fee "
                   + "FROM appointments a "
                   + "JOIN patients p ON a.patient_id = p.patient_id "
                   + "JOIN dentists d ON a.dentist_id = d.dentist_id "
                   + "JOIN treatments t ON a.treatment_id = t.treatment_id "
                   + "ORDER BY a.appointment_date, a.appointment_time";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(
                        resultSet.getInt("patient_id")
                );

                patient.setName(
                        resultSet.getString("patient_name")
                );

                patient.setAddress(
                        resultSet.getString("address")
                );

                patient.setContactNumber(
                        resultSet.getString("contact_number")
                );

                Dentist dentist = new Dentist();

                dentist.setDentistId(
                        resultSet.getInt("dentist_id")
                );

                dentist.setName(
                        resultSet.getString("dentist_name")
                );

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

                Appointment appointment = new Appointment();

                appointment.setAppointmentNo(
                        resultSet.getInt("appointment_no")
                );

                appointment.setAppointmentDate(
                        resultSet.getDate("appointment_date").toLocalDate()
                );

                appointment.setAppointmentTime(
                        resultSet.getTime("appointment_time").toLocalTime()
                );

                appointment.setStatus(
                        resultSet.getString("status")
                );

                appointment.setPatient(patient);
                appointment.setDentist(dentist);
                appointment.setTreatment(treatment);

                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}