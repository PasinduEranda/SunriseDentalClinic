<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.sunrise.dental.model.Appointment"%>
<%@ page import="com.sunrise.dental.model.Patient"%>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Appointment> appointments =
        (List<Appointment>) request.getAttribute("appointments");

    List<Patient> patients =
        (List<Patient>) request.getAttribute("patients");
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Sunrise Dental Clinic - Reports</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fb;
            margin: 0;
        }

        .header {
            background-color: #1976d2;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0;
        }

        .nav {
            margin-top: 15px;
        }

        .nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
        }

        .container {
            width: 95%;
            max-width: 1200px;
            margin: 30px auto;
        }

        .card {
            background-color: white;
            padding: 25px;
            margin-bottom: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        h2 {
            color: #1976d2;
            margin-top: 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th {
            background-color: #1976d2;
            color: white;
            padding: 12px;
            text-align: left;
        }

        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f9ff;
        }

        .empty {
            color: #777;
            padding: 15px 0;
        }

        .print-button {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 10px 18px;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 15px;
        }

        .print-button:hover {
            background-color: #125ca1;
        }

        @media print {

            .header,
            .nav,
            .print-button {
                display: none;
            }

            body {
                background-color: white;
            }

            .card {
                box-shadow: none;
            }

        }

    </style>

</head>

<body>

<div class="header">

    <h1>Sunrise Dental Clinic</h1>

    <p>Appointment & Patient Management System</p>

    <div class="nav">
        <a href="appointment.jsp">Appointments</a>
        <a href="bill.jsp">Billing</a>
        <a href="reports">Reports</a>
        <a href="help.jsp">Help</a>
        <a href="login.jsp">Logout</a>
    </div>

</div>


<div class="container">

    <!-- Appointment Report -->

    <div class="card">

        <h2>Appointment Report</h2>

        <button class="print-button"
                onclick="window.print()">
            Print Report
        </button>

        <%
            if (appointments != null && !appointments.isEmpty()) {
        %>

        <table>

            <thead>

                <tr>
                    <th>Appointment No</th>
                    <th>Patient</th>
                    <th>Contact</th>
                    <th>Dentist</th>
                    <th>Treatment</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                </tr>

            </thead>

            <tbody>

            <%
                for (Appointment appointment : appointments) {
            %>

                <tr>

                    <td>
                        <%= appointment.getAppointmentNo() %>
                    </td>

                    <td>
                        <%= appointment.getPatient().getName() %>
                    </td>

                    <td>
                        <%= appointment.getPatient().getContactNumber() %>
                    </td>

                    <td>
                        <%= appointment.getDentist().getName() %>
                    </td>

                    <td>
                        <%= appointment.getTreatment().getType() %>
                    </td>

                    <td>
                        <%= appointment.getAppointmentDate() %>
                    </td>

                    <td>
                        <%= appointment.getAppointmentTime() %>
                    </td>

                    <td>
                        <%= appointment.getStatus() %>
                    </td>

                </tr>

            <%
                }
            %>

            </tbody>

        </table>

        <%
            } else {
        %>

            <p class="empty">
                No appointments found.
            </p>

        <%
            }
        %>

    </div>


    <!-- Patient Report -->

    <div class="card">

        <h2>Patient Report</h2>

        <%
            if (patients != null && !patients.isEmpty()) {
        %>

        <table>

            <thead>

                <tr>
                    <th>Patient ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Contact Number</th>
                </tr>

            </thead>

            <tbody>

            <%
                for (Patient patient : patients) {
            %>

                <tr>

                    <td>
                        <%= patient.getPatientId() %>
                    </td>

                    <td>
                        <%= patient.getName() %>
                    </td>

                    <td>
                        <%= patient.getAddress() %>
                    </td>

                    <td>
                        <%= patient.getContactNumber() %>
                    </td>

                </tr>

            <%
                }
            %>

            </tbody>

        </table>

        <%
            } else {
        %>

            <p class="empty">
                No patients found.
            </p>

        <%
            }
        %>

    </div>
<!-- Billing Report -->
<div class="card">

    <h2>Billing Report</h2>

    <%
        java.util.List<com.sunrise.dental.model.Bill> bills =
            (java.util.List<com.sunrise.dental.model.Bill>)
            request.getAttribute("bills");

        if (bills != null && !bills.isEmpty()) {
    %>

    <table>

        <tr>
            <th>Bill ID</th>
            <th>Appointment No</th>
            <th>Treatment Cost</th>
            <th>Consultation Fee</th>
            <th>Total Amount</th>
        </tr>

        <%
            for (com.sunrise.dental.model.Bill bill : bills) {
        %>

        <tr>

            <td>
                <%= bill.getBillId() %>
            </td>

            <td>
                <%= bill.getAppointment().getAppointmentNo() %>
            </td>

            <td>
                Rs. <%= String.format("%.2f", bill.getTreatmentCost()) %>
            </td>

            <td>
                Rs. <%= String.format("%.2f", bill.getConsultationFee()) %>
            </td>

            <td>
                <strong>
                    Rs. <%= String.format("%.2f", bill.getTotalAmount()) %>
                </strong>
            </td>

        </tr>

        <%
            }
        %>

    </table>

    <%
        } else {
    %>

        <p>No billing records found.</p>

    <%
        }
    %>

</div>
</div>

</body>
</html>