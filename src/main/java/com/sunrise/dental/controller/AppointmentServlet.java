package com.sunrise.dental.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;
import com.sunrise.dental.service.AppointmentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AppointmentService appointmentService;

    @Override
    public void init() throws ServletException {
        appointmentService = new AppointmentService();
    }

    // Handle appointment registration
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("register".equals(action)) {

            try {
                // Patient details
                String patientName = request.getParameter("patientName");
                String address = request.getParameter("address");
                String contactNumber = request.getParameter("contactNumber");

                // Dentist, treatment and appointment details
                int dentistId = Integer.parseInt(request.getParameter("dentistId"));
                int treatmentId = Integer.parseInt(request.getParameter("treatmentId"));

                LocalDate appointmentDate =
                        LocalDate.parse(request.getParameter("appointmentDate"));

                LocalTime appointmentTime =
                        LocalTime.parse(request.getParameter("appointmentTime"));

                // Create Patient object
                Patient patient = new Patient();
                patient.setName(patientName);
                patient.setAddress(address);
                patient.setContactNumber(contactNumber);

                // Create Dentist object
                Dentist dentist = new Dentist();
                dentist.setDentistId(dentistId);

                // Create Treatment object
                Treatment treatment = new Treatment();
                treatment.setTreatmentId(treatmentId);

                // Create Appointment object
                Appointment appointment = new Appointment();
                appointment.setPatient(patient);
                appointment.setDentist(dentist);
                appointment.setTreatment(treatment);
                appointment.setAppointmentDate(appointmentDate);
                appointment.setAppointmentTime(appointmentTime);
                appointment.setStatus("Scheduled");

                // Send appointment to service layer
                boolean success =
                        appointmentService.registerAppointment(appointment);

                if (success) {

                    request.setAttribute(
                            "successMessage",
                            "Appointment registered successfully. Appointment No: "
                                    + appointment.getAppointmentNo()
                    );

                } else {

                    request.setAttribute(
                            "errorMessage",
                            "Appointment registration failed. "
                            + "The dentist may already be booked or the entered data is invalid."
                    );
                }

            } catch (Exception e) {

                request.setAttribute(
                        "errorMessage",
                        "Invalid appointment details. Please check your input."
                );
            }

            request.getRequestDispatcher("appointment.jsp")
                   .forward(request, response);

        } else {

            response.sendRedirect("appointment.jsp");
        }
    }

    // Handle appointment search
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appointmentNoText = request.getParameter("appointmentNo");

        if (appointmentNoText != null && !appointmentNoText.trim().isEmpty()) {

            try {

                int appointmentNo = Integer.parseInt(appointmentNoText);

                Appointment appointment =
                        appointmentService.searchAppointment(appointmentNo);

                if (appointment != null) {

                    request.setAttribute("appointment", appointment);

                } else {

                    request.setAttribute(
                            "errorMessage",
                            "Appointment not found."
                    );
                }

            } catch (NumberFormatException e) {

                request.setAttribute(
                        "errorMessage",
                        "Please enter a valid appointment number."
                );
            }
        }

        request.getRequestDispatcher("appointment.jsp")
               .forward(request, response);
    }
}