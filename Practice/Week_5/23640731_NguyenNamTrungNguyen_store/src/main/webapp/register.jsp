<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2026
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica, sans-serif;
            background-color: #ffffff;
        }

        .register-container {
            width: 360px;
            margin: 80px auto;
            padding: 22px 16px 16px 16px;

            border: 1px solid #7ac943;
            border-radius: 0;
            background-color: #fff;
        }

        .register-title {
            text-align: center;
            font-size: 22px;
            font-weight: bold;
            color: #222;
            margin-bottom: 18px;
        }

        /* Hàng First Name - Last Name */
        .name-row {
            display: flex;
            gap: 15px;
            margin-bottom: 10px;
        }

        .name-row .form-control {
            width: 50%;
        }

        /* Input */
        .form-control {
            width: 100%;
            height: 38px;
            padding: 8px 10px;

            border: 1px solid #e0e0e0;
            border-radius: 5px;

            font-size: 12px;
            color: #333;
            outline: none;
        }

        .form-control::placeholder {
            color: #777;
        }

        .form-control:focus {
            border-color: #1877f2;
            box-shadow: 0 0 0 1px #1877f2;
        }

        .form-group {
            margin-bottom: 10px;
        }

        /* Birthday */
        .birthday-label {
            display: block;
            font-size: 12px;
            color: #333;
            margin-bottom: 7px;
        }

        .birthday-row {
            display: flex;
            gap: 15px;
            margin-bottom: 14px;
        }

        .birthday-row select {
            flex: 1;
            height: 26px;

            padding: 3px 8px;

            border: 1px solid #ddd;
            border-radius: 4px;

            background-color: white;
            color: #444;

            font-size: 12px;
            outline: none;
        }

        .birthday-row select:focus {
            border-color: #1877f2;
        }

        /* Gender */
        .gender-label {
            display: block;
            font-size: 12px;
            color: #333;
            margin-bottom: 7px;
        }

        .gender-row {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-bottom: 15px;
        }

        .gender-option {
            display: flex;
            align-items: center;
            gap: 5px;

            font-size: 12px;
            color: #444;
        }

        .gender-option input {
            margin: 0;
            width: 13px;
            height: 13px;
        }

        /* Button */
        .signup-btn {
            width: 100%;
            height: 32px;

            border: none;
            border-radius: 6px;

            background-color: #1877f2;
            color: white;

            font-size: 14px;
            cursor: pointer;

            transition: background-color 0.2s;
        }

        .signup-btn:hover {
            background-color: #166fe5;
        }

        .signup-btn:active {
            background-color: #125bc0;
        }
    </style>
</head>

<body>

<div class="register-container">

    <div class="register-title">
        User Registration Form
    </div>

    <form action="${pageContext.request.contextPath}/registerform" method="post">

    <!-- First Name + Last Name -->
        <div class="name-row">
            <input
                    type="text"
                    name="firstname"
                    class="form-control"
                    placeholder="First Name"
                    required>

            <input
                    type="text"
                    name="lastname"
                    class="form-control"
                    placeholder="Last Name"
                    required>
        </div>

        <!-- Email -->
        <div class="form-group">
            <input
                    type="email"
                    name="email"
                    class="form-control"
                    placeholder="Your Email"
                    required>
        </div>

        <!-- Password -->
        <div class="form-group">
            <input
                    type="password"
                    name="password"
                    class="form-control"
                    placeholder="Password"
                    required>
        </div>

        <!-- Birthday -->
        <label class="birthday-label">
            Birthday
        </label>

        <div class="birthday-row">

            <select name="month" required>
                <option value="">Month</option>
                <option value="01">January</option>
                <option value="02">February</option>
                <option value="03">March</option>
                <option value="04">April</option>
                <option value="05">May</option>
                <option value="06">June</option>
                <option value="07">July</option>
                <option value="08">August</option>
                <option value="09">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select>

            <select name="day" required>
                <option value="">Day</option>

                <%
                    for (int i = 1; i <= 31; i++) {
                %>
                <option value="<%= i %>">
                    <%= i %>
                </option>
                <%
                    }
                %>
            </select>

            <select name="year" required>
                <option value="">Year</option>

                <%
                    for (int year = 2025; year >= 1950; year--) {
                %>
                <option value="<%= year %>">
                    <%= year %>
                </option>
                <%
                    }
                %>
            </select>

        </div>

        <!-- Gender -->
        <label class="gender-label">
            Gender
        </label>

        <div class="gender-row">

            <label class="gender-option">
                <input
                        type="radio"
                        name="gender"
                        value="Female"
                        required>
                Female
            </label>

            <label class="gender-option">
                <input
                        type="radio"
                        name="gender"
                        value="Male">
                Male
            </label>

        </div>

        <!-- Sign Up -->
        <button type="submit" class="signup-btn">
            Sign Up
        </button>

    </form>

</div>

</body>
</html>

