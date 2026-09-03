<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sunrise Dental Clinic - Billing</title>

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
            width: 90%;
            max-width: 900px;
            margin: 30px auto;
        }

        .card {
            background: white;
            padding: 25px;
            margin-bottom: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        h2 {
            color: #1976d2;
            margin-top: 0;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 11px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #125ca1;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .receipt {
            border: 1px solid #ddd;
            padding: 25px;
            background-color: #fff;
        }

        .receipt-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .total {
            font-size: 20px;
            font-weight: bold;
            color: #1976d2;
            border-bottom: none;
        }

        .print-button {
            margin-top: 20px;
        }

        @media print {
            .header,
            .search-card,
            .print-button {
                display: none;
            }

            body {
                background-color: white;
            }

            .container {
                width: 100%;
                margin: 0;
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
        <a href="appointment">Appointments</a>
        <a href="billing">Billing</a>
        <a href="reports">Reports</a>
        <a href="help.jsp">Help</a>
        <a href="logout">Logout</a>
    </div>

</div>


<div class="container">

    <!-- Search Appointment -->
    <div class="card search-card">

        <h2>Generate Bill</h2>

        <form action="billing" method="get">

            <div class="form-group">

                <label for="appointmentNo">
                    Appointment Number
                </label>

                <input type="number"
                       id="appointmentNo"
                       name="appointmentNo"
                       min="1"
                       required
                       placeholder="Enter appointment number">

            </div>

            <button type="submit">
                Calculate Bill
            </button>

        </form>

    </div>


    <!-- Error Message -->

    <%
        if (request.getAttribute("errorMessage") != null) {
    %>

        <div class="error">
            <%= request.getAttribute("errorMessage") %>
        </div>

    <%
        }
    %>


    <!-- Bill -->

    <%
        com.sunrise.dental.model.Bill bill =
            (com.sunrise.dental.model.Bill)
            request.getAttribute("bill");

        if (bill != null) {
    %>

    <div class="card">

        <div class="receipt">

            <h2>Dental Clinic Bill / Receipt</h2>

            <hr>

            <%
                if (bill.getAppointment() != null) {
            %>

                <p>
                    <strong>Appointment No:</strong>
                    <%= bill.getAppointment().getAppointmentNo() %>
                </p>

                <p>
                    <strong>Patient:</strong>
                    <%= bill.getAppointment().getPatient().getName() %>
                </p>

                <p>
                    <strong>Dentist:</strong>
                    <%= bill.getAppointment().getDentist().getName() %>
                </p>

                <p>
                    <strong>Treatment:</strong>
                    <%= bill.getAppointment().getTreatment().getType() %>
                </p>

                <p>
                    <strong>Date:</strong>
                    <%= bill.getAppointment().getAppointmentDate() %>
                </p>

            <%
                }
            %>

            <hr>

            <div class="receipt-row">

                <span>Treatment Cost</span>

                <span>
                    Rs. <%= String.format("%.2f", bill.getTreatmentCost()) %>
                </span>

            </div>

            <div class="receipt-row">

                <span>Consultation Fee</span>

                <span>
                    Rs. <%= String.format("%.2f", bill.getConsultationFee()) %>
                </span>

            </div>

            <div class="receipt-row total">

                <span>Total Amount</span>

                <span>
                    Rs. <%= String.format("%.2f", bill.getTotalAmount()) %>
                </span>

            </div>

            <button class="print-button"
                    onclick="window.print()">
                Print Receipt
            </button>

        </div>

    </div>

    <%
        }
    %>

</div>

</body>
</html>