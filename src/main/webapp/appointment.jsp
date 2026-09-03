<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // Check whether clinic staff is logged in
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sunrise Dental Clinic - Appointments</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #1976d2;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .container {
            width: 90%;
            max-width: 1000px;
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

        input, select {
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

        .success {
            background-color: #d4edda;
            color: #155724;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .appointment-details {
            background-color: #eef5ff;
            padding: 15px;
            border-radius: 5px;
        }

        .nav {
            margin-top: 15px;
        }

        .nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
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
        <a href="reports.jsp">Reports</a>
        <a href="login.jsp">Logout</a>
    </div>
</div>

<div class="container">

    <!-- Success message -->
    <% if (request.getAttribute("successMessage") != null) { %>
        <div class="success">
            <%= request.getAttribute("successMessage") %>
        </div>
    <% } %>

    <!-- Error message -->
    <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error">
            <%= request.getAttribute("errorMessage") %>
        </div>
    <% } %>


    <!-- Register Appointment -->
    <div class="card">

        <h2>Register New Appointment</h2>

        <form action="appointment" method="post">

            <input type="hidden" name="action" value="register">

            <div class="form-group">
                <label>Patient Name</label>
                <input type="text"
                       name="patientName"
                       required
                       maxlength="100"
                       placeholder="Enter patient name">
            </div>

            <div class="form-group">
                <label>Address</label>
                <input type="text"
                       name="address"
                       maxlength="255"
                       placeholder="Enter patient address">
            </div>

            <div class="form-group">
                <label>Contact Number</label>
                <input type="text"
                       name="contactNumber"
                       required
                       maxlength="20"
                       placeholder="Enter contact number">
            </div>

            <div class="form-group">
                <label>Dentist</label>

                <select name="dentistId" required>
                    <option value="">-- Select Dentist --</option>
                    <option value="1">Dr. John Perera</option>
                    <option value="2">Dr. Sarah Fernando</option>
                </select>
            </div>

            <div class="form-group">
                <label>Treatment</label>

                <select name="treatmentId" required>
                    <option value="">-- Select Treatment --</option>
                    <option value="1">Dental Checkup - Rs. 2,500</option>
                    <option value="2">Teeth Cleaning - Rs. 5,000</option>
                    <option value="3">Tooth Filling - Rs. 7,500</option>
                    <option value="4">Tooth Extraction - Rs. 10,000</option>
                    <option value="5">Root Canal Treatment - Rs. 25,000</option>
                </select>
            </div>

            <div class="form-group">
                <label>Appointment Date</label>
                <input type="date"
                       name="appointmentDate"
                       required>
            </div>

            <div class="form-group">
                <label>Appointment Time</label>
                <input type="time"
                       name="appointmentTime"
                       required>
            </div>

            <button type="submit">Register Appointment</button>

        </form>

    </div>


    <!-- Search Appointment -->
    <div class="card">

        <h2>Search Appointment</h2>

        <form action="appointment" method="get">

            <div class="form-group">
                <label>Appointment Number</label>

                <input type="number"
                       name="appointmentNo"
                       min="1"
                       required
                       placeholder="Enter appointment number">
            </div>

            <button type="submit">Search Appointment</button>

        </form>

    </div>


    <!-- Search Result -->
    <%
        com.sunrise.dental.model.Appointment appointment =
            (com.sunrise.dental.model.Appointment)
            request.getAttribute("appointment");

        if (appointment != null) {
    %>

    <div class="card">

        <h2>Appointment Details</h2>

        <div class="appointment-details">

            <p>
                <strong>Appointment No:</strong>
                <%= appointment.getAppointmentNo() %>
            </p>

            <p>
                <strong>Patient:</strong>
                <%= appointment.getPatient().getName() %>
            </p>

            <p>
                <strong>Contact:</strong>
                <%= appointment.getPatient().getContactNumber() %>
            </p>

            <p>
                <strong>Dentist:</strong>
                <%= appointment.getDentist().getName() %>
            </p>

            <p>
                <strong>Treatment:</strong>
                <%= appointment.getTreatment().getType() %>
            </p>

            <p>
                <strong>Treatment Fee:</strong>
                Rs. <%= appointment.getTreatment().getFee() %>
            </p>

            <p>
                <strong>Date:</strong>
                <%= appointment.getAppointmentDate() %>
            </p>

            <p>
                <strong>Time:</strong>
                <%= appointment.getAppointmentTime() %>
            </p>

            <p>
                <strong>Status:</strong>
                <%= appointment.getStatus() %>
            </p>

        </div>

    </div>

    <% } %>

</div>

</body>
</html>