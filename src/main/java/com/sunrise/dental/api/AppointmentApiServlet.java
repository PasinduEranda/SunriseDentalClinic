package com.sunrise.dental.api;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.service.AppointmentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/api/appointments")
public class AppointmentApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AppointmentService appointmentService;

    @Override
    public void init() throws ServletException {
        appointmentService = new AppointmentService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether the staff member is logged in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                "{\"error\":\"Unauthorized. Please login first.\"}"
            );
            return;
        }

        String appointmentNoParam =
                request.getParameter("appointmentNo");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // Validate appointment number
        if (appointmentNoParam == null ||
            appointmentNoParam.trim().isEmpty()) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.print(
                "{\"error\":\"Appointment number is required.\"}"
            );

            return;
        }

        int appointmentNo;

        try {
            appointmentNo = Integer.parseInt(appointmentNoParam);

        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.print(
                "{\"error\":\"Appointment number must be a valid number.\"}"
            );

            return;
        }

        // Search appointment
        Appointment appointment =
                appointmentService.searchAppointment(appointmentNo);

        if (appointment == null) {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            out.print(
                "{\"error\":\"Appointment not found.\"}"
            );

            return;
        }

        // Return appointment as JSON
        out.print("{");

        out.print("\"appointmentNo\":"
                + appointment.getAppointmentNo() + ",");

        if (appointment.getPatient() != null) {
            out.print("\"patientName\":\""
                    + escapeJson(
                        appointment.getPatient().getName())
                    + "\",");
        } else {
            out.print("\"patientName\":\"\",");
        }

        if (appointment.getPatient() != null) {
            out.print("\"contactNumber\":\""
                    + escapeJson(
                        appointment.getPatient().getContactNumber())
                    + "\",");
        } else {
            out.print("\"contactNumber\":\"\",");
        }

        if (appointment.getDentist() != null) {
            out.print("\"dentist\":\""
                    + escapeJson(
                        appointment.getDentist().getName())
                    + "\",");
        } else {
            out.print("\"dentist\":\"\",");
        }

        if (appointment.getTreatment() != null) {
            out.print("\"treatment\":\""
                    + escapeJson(
                        appointment.getTreatment().getType())
                    + "\",");

            out.print("\"treatmentFee\":"
                    + appointment.getTreatment().getFee() + ",");
        } else {
            out.print("\"treatment\":\"\",");
            out.print("\"treatmentFee\":0,");
        }

        out.print("\"appointmentDate\":\""
                + appointment.getAppointmentDate() + "\",");

        out.print("\"appointmentTime\":\""
                + appointment.getAppointmentTime() + "\",");

        out.print("\"status\":\""
                + escapeJson(appointment.getStatus())
                + "\"");

        out.print("}");
    }

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}