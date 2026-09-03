<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sunrise Dental Clinic - Login</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 0;
        }

        .login-container {
            width: 400px;
            margin: 100px auto;
            background: white;
            padding: 35px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.15);
        }

        h1 {
            text-align: center;
            color: #1976d2;
            margin-bottom: 5px;
        }

        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 11px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
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

        .footer {
            text-align: center;
            margin-top: 25px;
            color: #777;
            font-size: 13px;
        }
    </style>
</head>

<body>

<div class="login-container">

    <h1>Sunrise Dental Clinic</h1>

    <div class="subtitle">
        Clinic Appointment & Patient Management System
    </div>

    <% if (request.getAttribute("errorMessage") != null) { %>

        <div class="error">
            <%= request.getAttribute("errorMessage") %>
        </div>

    <% } %>

    <form action="login" method="post">

        <div class="form-group">

            <label for="username">Username</label>

            <input type="text"
                   id="username"
                   name="username"
                   required
                   maxlength="50"
                   placeholder="Enter username">

        </div>

        <div class="form-group">

            <label for="password">Password</label>

            <input type="password"
                   id="password"
                   name="password"
                   required
                   maxlength="255"
                   placeholder="Enter password">

        </div>

        <button type="submit">
            Login
        </button>

    </form>

    <div class="footer">
        Sunrise Dental Clinic © 2026
    </div>

</div>

</body>
</html>