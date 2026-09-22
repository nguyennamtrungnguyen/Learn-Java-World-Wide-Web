<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2026
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>
    <title>Account List</title>
</head>

<body>

    <h2>Account List</h2>
    <a href="${pageContext.request.contextPath}/registerform">
        Đăng Ký Tài Khoản
    </a>
    <table border="1">

        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Date of Birth</th>
        </tr>

        <c:forEach
                var="account"
                items="${accounts}">

            <tr>

                <td>${account.id}</td>

                <td>${account.firstName}</td>

                <td>${account.lastName}</td>

                <td>${account.email}</td>

                <td>${account.dateOfBirth}</td>

            </tr>

        </c:forEach>

        </table>


    </body>
</html>

