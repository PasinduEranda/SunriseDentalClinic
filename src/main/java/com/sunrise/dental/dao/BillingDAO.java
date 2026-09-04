package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.config.DBConnection;
import com.sunrise.dental.model.Bill;

public class BillingDAO {

    private Connection connection;

    public BillingDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    // Save a bill to the database
    public boolean save(Bill bill) {

        String sql = "INSERT INTO bills "
                   + "(appointment_no, treatment_cost, consultation_fee, total_amount) "
                   + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(
                sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(
                    1,
                    bill.getAppointment().getAppointmentNo()
            );

            statement.setDouble(
                    2,
                    bill.getTreatmentCost()
            );

            statement.setDouble(
                    3,
                    bill.getConsultationFee()
            );

            statement.setDouble(
                    4,
                    bill.getTotalAmount()
            );

            int rows = statement.executeUpdate();

            if (rows > 0) {

                try (ResultSet resultSet =
                        statement.getGeneratedKeys()) {

                    if (resultSet.next()) {

                        bill.setBillId(
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

    // Find a bill by appointment number
    public Bill findByAppointmentNo(int appointmentNo) {

        String sql = "SELECT * FROM bills WHERE appointment_no = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentNo);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Bill bill = new Bill();

                    bill.setBillId(
                            resultSet.getInt("bill_id")
                    );

                    bill.setTreatmentCost(
                            resultSet.getDouble("treatment_cost")
                    );

                    bill.setConsultationFee(
                            resultSet.getDouble("consultation_fee")
                    );

                    bill.setTotalAmount(
                            resultSet.getDouble("total_amount")
                    );

                    return bill;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

 // Get all bills for the billing report
    public List<Bill> findAll() {

        List<Bill> bills = new ArrayList<>();

        String sql = "SELECT "
                   + "bill_id, "
                   + "appointment_no, "
                   + "treatment_cost, "
                   + "consultation_fee, "
                   + "total_amount "
                   + "FROM bills "
                   + "ORDER BY bill_id";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Bill bill = new Bill();

                bill.setBillId(
                        resultSet.getInt("bill_id")
                );

                bill.setTreatmentCost(
                        resultSet.getDouble("treatment_cost")
                );

                bill.setConsultationFee(
                        resultSet.getDouble("consultation_fee")
                );

                bill.setTotalAmount(
                        resultSet.getDouble("total_amount")
                );

                // Create an Appointment object
                // so the billing report can display
                // the appointment number.
                com.sunrise.dental.model.Appointment appointment =
                        new com.sunrise.dental.model.Appointment();

                appointment.setAppointmentNo(
                        resultSet.getInt("appointment_no")
                );

                bill.setAppointment(appointment);

                bills.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }
}