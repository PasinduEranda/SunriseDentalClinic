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

        /* Header */

        .header {
            background-color: #1976d2;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0;
        }

        .header p {
            margin: 8px 0 15px;
        }

        /* Navigation */

        .nav {
            margin-top: 15px;
        }

        .nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
            font-weight: bold;
        }

        .nav a:hover {
            text-decoration: underline;
        }

        /* Main container */

        .container {
            width: 90%;
            max-width: 900px;
            margin: 30px auto;
        }

        /* Cards */

        .card {
            background: white;
            padding: 25px;
            margin-bottom: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #1976d2;
            margin-top: 0;
        }

        /* Form */

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
            padding: 11px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 15px;
        }

        input:focus {
            outline: none;
            border-color: #1976d2;
        }

        button {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 11px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        button:hover {
            background-color: #125ca1;
        }

        /* Messages */

        .success {
            background-color: #d4edda;
            color: #155724;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #f5c6cb;
        }

        /* Receipt */

        .receipt {
            border: 1px solid #ddd;
            padding: 30px;
            background-color: white;
        }

        .receipt-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .receipt-header h2 {
            margin-bottom: 5px;
        }

        .receipt-header p {
            margin: 5px;
            color: #666;
        }

        /* Appointment information */

        .appointment-details {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .detail-row {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
        }

        .detail-label {
            font-weight: bold;
        }

        /* Bill rows */

        .receipt-row {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px solid #eee;
        }

        .total {
            font-size: 20px;
            font-weight: bold;
            color: #1976d2;
            border-bottom: none;
            padding-top: 18px;
        }

        /* Print button */

        .print-button {
            margin-top: 20px;
        }

        /* Footer */

        .receipt-footer {
            text-align: center;
            margin-top: 30px;
            color: #777;
            font-size: 13px;
        }

        /* Mobile */

        @media (max-width: 600px) {

            .container {
                width: 95%;
            }

            .nav a {
                display: inline-block;
                margin: 5px;
            }

            .detail-row,
            .receipt-row {
                flex-direction: column;
                gap: 5px;
            }

            .receipt {
                padding: 20px;
            }
        }

        /* Print */

        @media print {

            .header,
            .search-card,
            .print-button,
            .success,
            .error {
                display: none;
            }

            body {
                background-color: white;
            }

            .container {
                width: 100%;
                max-width: none;
                margin: 0;
            }

            .card {
                box-shadow: none;
                margin: 0;
                padding: 0;
            }

            .receipt {
                border: none;
                padding: 20px;
            }

        }

    </style>

</head>

<body>


<!-- Header -->

<div class="header">

    <h1>Sunrise Dental Clinic</h1>

    <p>Appointment & Patient Management System</p>

    <div class="nav">

        <a href="appointment">Appointments</a>

        <a href="billing">Billing</a>

        <a href="reports">Reports</a>

        <a href="help">Help</a>

        <a href="logout">Logout</a>

    </div>

</div>


<!-- Main Container -->

<div class="container">


    <!-- Search / Generate Bill -->

    <div class="card search-card">

        <h2>Generate Bill</h2>

        <p>
            Enter an appointment number to calculate or retrieve
            the patient's bill.
        </p>

        <form action="billing" method="get">

            <div class="form-group">

                <label for="appointmentNo">
                    Appointment Number
                </label>

                <input
                    type="number"
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


    <!-- Success Message -->

    <%
        if (request.getAttribute("successMessage") != null) {
    %>

        <div class="success">

            <%= request.getAttribute("successMessage") %>

        </div>

    <%
        }
    %>


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


            <!-- Receipt Header -->

            <div class="receipt-header">

                <h2>
                    SUNRISE DENTAL CLINIC
                </h2>

                <p>
                    Dental Clinic Bill / Receipt
                </p>

            </div>


            <hr>


            <!-- Appointment Information -->

            <%
                if (bill.getAppointment() != null) {
            %>

            <div class="appointment-details">

                <div class="detail-row">

                    <span class="detail-label">
                        Bill ID:
                    </span>

                    <span>
                        <%= bill.getBillId() %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Appointment No:
                    </span>

                    <span>
                        <%= bill.getAppointment().getAppointmentNo() %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Patient:
                    </span>

                    <span>
                        <%= bill.getAppointment()
                                .getPatient()
                                .getName() %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Dentist:
                    </span>

                    <span>
                        <%= bill.getAppointment()
                                .getDentist()
                                .getName() %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Treatment:
                    </span>

                    <span>
                        <%= bill.getAppointment()
                                .getTreatment()
                                .getType() %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Appointment Date:
                    </span>

                    <span>
                        <%= bill.getAppointment()
                                .getAppointmentDate() %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Appointment Time:
                    </span>

                    <span>
                        <%= bill.getAppointment()
                                .getAppointmentTime() %>
                    </span>

                </div>

            </div>

            <%
                }
            %>


            <hr>


            <!-- Billing Details -->

            <div class="receipt-row">

                <span>
                    Treatment Cost
                </span>

                <span>
                    Rs.
                    <%= String.format(
                            "%.2f",
                            bill.getTreatmentCost()
                        ) %>
                </span>

            </div>


            <div class="receipt-row">

                <span>
                    Consultation Fee
                </span>

                <span>
                    Rs.
                    <%= String.format(
                            "%.2f",
                            bill.getConsultationFee()
                        ) %>
                </span>

            </div>


            <div class="receipt-row total">

                <span>
                    Total Amount
                </span>

                <span>
                    Rs.
                    <%= String.format(
                            "%.2f",
                            bill.getTotalAmount()
                        ) %>
                </span>

            </div>


            <!-- Print -->

            <button
                class="print-button"
                onclick="window.print()">

                Print Receipt

            </button>


            <!-- Footer -->

            <div class="receipt-footer">

                <p>
                    Thank you for choosing Sunrise Dental Clinic.
                </p>

                <p>
                    This is a computer-generated receipt.
                </p>

            </div>


        </div>

    </div>


    <%
        }
    %>


</div>

</body>
</html>