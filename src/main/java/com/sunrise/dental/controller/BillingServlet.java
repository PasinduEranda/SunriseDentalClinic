package com.sunrise.dental.controller;

import java.io.IOException;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.service.AppointmentService;
import com.sunrise.dental.service.BillingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AppointmentService appointmentService;
    private BillingService billingService;

    @Override
    public void init() throws ServletException {
        appointmentService = new AppointmentService();
        billingService = new BillingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether staff is logged in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String appointmentNoText = request.getParameter("appointmentNo");

        if (appointmentNoText == null || appointmentNoText.trim().isEmpty()) {

            request.setAttribute(
                    "errorMessage",
                    "Please enter an appointment number."
            );

            request.getRequestDispatcher("bill.jsp")
                   .forward(request, response);

            return;
        }

        try {

            int appointmentNo = Integer.parseInt(appointmentNoText);

            Appointment appointment =
                    appointmentService.searchAppointment(appointmentNo);

            if (appointment == null) {

                request.setAttribute(
                        "errorMessage",
                        "Appointment not found."
                );

            } else {

                Bill bill = billingService.calculateBill(appointment);

                if (bill != null) {

                    request.setAttribute("bill", bill);

                } else {

                    request.setAttribute(
                            "errorMessage",
                            "Unable to calculate the bill."
                    );
                }
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "errorMessage",
                    "Please enter a valid appointment number."
            );
        }

        request.getRequestDispatcher("bill.jsp")
               .forward(request, response);
    }
}