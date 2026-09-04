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
<title>Help - Sunrise Dental Clinic</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f7fb;
        margin: 0;
    }

    .navbar {
        background: #1976d2;
        padding: 18px 30px;
        color: white;
    }

    .navbar h2 {
        display: inline;
        margin-right: 40px;
    }

    .navbar a {
        color: white;
        text-decoration: none;
        margin-right: 20px;
    }

    .container {
        width: 85%;
        margin: 30px auto;
    }

    .card {
        background: white;
        padding: 25px;
        margin-bottom: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.08);
    }

    h1 {
        color: #1976d2;
    }

    h3 {
        color: #333;
    }

    li {
        margin: 10px 0;
    }

    .note {
        background: #eef6ff;
        padding: 15px;
        border-left: 5px solid #1976d2;
        margin-top: 15px;
    }
</style>

</head>

<body>

<div class="navbar">
    <h2>Sunrise Dental Clinic</h2>

    <a href="appointment">Appointments</a>
    <a href="billing">Billing</a>
    <a href="reports">Reports</a>
    <a href="help">Help</a>
    <a href="logout">Logout</a>
</div>

<div class="container">

    <div class="card">
        <h1>Help & User Guide</h1>

        <p>
            Welcome to the Sunrise Dental Clinic Appointment and
            Patient Management System.
        </p>

        <p>
            This page provides basic instructions for clinic staff
            when using the system.
        </p>
    </div>


    <div class="card">
        <h3>1. Register a New Appointment</h3>

        <ol>
            <li>Open the <b>Appointments</b> page.</li>
            <li>Enter the patient's name.</li>
            <li>Enter the patient's address.</li>
            <li>Enter a valid 10-digit contact number.</li>
            <li>Select a dentist.</li>
            <li>Select the required treatment.</li>
            <li>Select a future appointment date.</li>
            <li>Select an appointment time.</li>
            <li>Click <b>Register Appointment</b>.</li>
        </ol>

        <div class="note">
            The system automatically generates a unique appointment
            number after successful registration.
        </div>
    </div>


    <div class="card">
        <h3>2. Search for an Appointment</h3>

        <ol>
            <li>Open the <b>Appointments</b> page.</li>
            <li>Enter the appointment number.</li>
            <li>Click <b>Search</b>.</li>
            <li>The patient's appointment details will be displayed.</li>
        </ol>
    </div>


    <div class="card">
        <h3>3. Billing</h3>

        <ol>
            <li>Open the <b>Billing</b> page.</li>
            <li>Enter the appointment number.</li>
            <li>Click <b>Generate Bill</b>.</li>
            <li>The system retrieves the treatment fee.</li>
            <li>The consultation fee is added automatically.</li>
            <li>The total bill amount is calculated.</li>
            <li>The receipt can be printed using the print option.</li>
        </ol>
    </div>


    <div class="card">
        <h3>4. Reports</h3>

        <p>The Reports page provides:</p>

        <ul>
            <li>Appointment Report</li>
            <li>Patient Report</li>
            <li>Billing Report</li>
        </ul>

        <p>
            Open <b>Reports</b> from the navigation bar to view
            the available records.
        </p>
    </div>


    <div class="card">
        <h3>5. Common Validation Messages</h3>

        <ul>
            <li>
                <b>Invalid patient name</b> –
                use letters and spaces only.
            </li>

            <li>
                <b>Invalid contact number</b> –
                enter exactly 10 digits.
            </li>

            <li>
                <b>Appointment date cannot be in the past</b> –
                select today or a future date.
            </li>

            <li>
                <b>Appointment time</b> –
                make sure a valid time is selected.
            </li>

            <li>
                <b>Doctor already booked</b> –
                select another available date or time.
            </li>
        </ul>
    </div>


    <div class="card">
        <h3>6. Security & Logout</h3>

        <p>
            Clinic staff must log in before accessing the system.
        </p>

        <p>
            When finished, click <b>Logout</b> to terminate the
            current session.
        </p>

        <div class="note">
            Always log out when using a shared clinic computer.
        </div>
    </div>


    <div class="card">
        <h3>7. Support</h3>

        <p>
            If a system error occurs, verify the entered information
            first. If the problem continues, contact the system
            administrator.
        </p>
    </div>

</div>

</body>
</html>