package com.sunrise.dental.controller;

import java.io.IOException;
import java.util.List;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.service.ReportService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ReportService reportService;

    @Override
    public void init() throws ServletException {
        reportService = new ReportService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether clinic staff is logged in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Generate appointment report
        List<Appointment> appointments =
                reportService.generateAppointmentReport();

        // Generate patient report
        List<Patient> patients =
                reportService.generatePatientReport();

        // Generate billing report
        List<Bill> bills =
                reportService.generateBillingReport();

        // Send reports to JSP
        request.setAttribute("appointments", appointments);
        request.setAttribute("patients", patients);
        request.setAttribute("bills", bills);

        // Display reports page
        request.getRequestDispatcher("reports.jsp")
               .forward(request, response);
    }
}