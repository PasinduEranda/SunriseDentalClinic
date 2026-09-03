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
import jakarta.servlet.http.HttpSession;

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
    	
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("user") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

        String action = request.getParameter("action");

        if ("register".equals(action)) {

            try {
                // Patient details
                String patientName = request.getParameter("patientName");
                String address = request.getParameter("address");
                String contactNumber = request.getParameter("contactNumber");
                
                if (!com.sunrise.dental.util.ValidationUtil.isValidName(patientName)) {

                    request.setAttribute("errorMessage",

                            "Invalid patient name. Please use letters and spaces only.");

                    request.getRequestDispatcher("appointment.jsp").forward(request, response);

                    return;

                }

                if (!com.sunrise.dental.util.ValidationUtil.isValidContactNumber(contactNumber)) {

                    request.setAttribute("errorMessage",

                            "Invalid contact number. Please enter exactly 10 digits.");

                    request.getRequestDispatcher("appointment.jsp").forward(request, response);

                    return;

                }

                // Dentist, treatment and appointment details
                int dentistId = Integer.parseInt(request.getParameter("dentistId"));
                int treatmentId = Integer.parseInt(request.getParameter("treatmentId"));

                String appointmentDateText = request.getParameter("appointmentDate");
                String appointmentTimeText = request.getParameter("appointmentTime");

                if (appointmentDateText == null || appointmentDateText.trim().isEmpty()) {
                    request.setAttribute("errorMessage",
                            "Please select an appointment date.");
                    request.getRequestDispatcher("appointment.jsp").forward(request, response);
                    return;
                }

                if (appointmentTimeText == null || appointmentTimeText.trim().isEmpty()) {
                    request.setAttribute("errorMessage",
                            "Please select an appointment time.");
                    request.getRequestDispatcher("appointment.jsp").forward(request, response);
                    return;
                }

                LocalDate appointmentDate = LocalDate.parse(appointmentDateText);
                LocalTime appointmentTime = LocalTime.parse(appointmentTimeText);

                if (appointmentDate.isBefore(LocalDate.now())) {
                    request.setAttribute("errorMessage",
                            "Appointment date cannot be in the past.");
                    request.getRequestDispatcher("appointment.jsp").forward(request, response);
                    return;
                }

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
                boolean available = appointmentService.checkAvailability(
                        dentistId,
                        appointmentDate,
                        appointmentTime
                );

                if (!available) {
                    request.setAttribute("errorMessage",
                            "This dentist is already booked for the selected date and time. "
                            + "Please choose another time.");
                } else {

                    boolean success = appointmentService.registerAppointment(appointment);

                    if (success) {
                        request.setAttribute("successMessage",
                                "Appointment registered successfully. Appointment No: "
                                        + appointment.getAppointmentNo());
                    } else {
                        request.setAttribute("errorMessage",
                                "Appointment registration failed. Please check the entered details.");
                    }
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
    	
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("user") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

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